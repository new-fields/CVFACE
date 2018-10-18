package com.cvface.serialCommunicationTest;

import org.springframework.boot.web.server.PortInUseException;

import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import gnu.io.UnsupportedCommOperationException;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.TooManyListenersException;

/**
 * 串口通信测试类
 * CommPortIdentifier 主要用于对串口进行管理和设置，是对串口进行访问控制的核心类
 * 每个 CommPortIdentifier object对应一个可用的串口设备。CommPortIdentifier 是访问单个串口设备的主要类。
 */
public class RxTxTest {
    public static void main(String[] args) throws Exception {
        CommPortIdentifier port = getSerialPort("COM6");
        //打印串口的名称
        System.out.println(port.getName());
        SerialPortClient client = new SerialPortClient(port);
        client.initAndOpen();

        client.send(new byte[]{(byte) 0x05});
        client.send(new byte[]{(byte) 0x02});

        for (int i = 0; i < 10; i++) {
            System.out.println(i);
            client.send(new byte[]{(byte) i});
            Thread.sleep(200);
        }
        client.close();
    }

    // 获取系统的所有串口
    public static Enumeration<CommPortIdentifier> listAllPort() {
        @SuppressWarnings("unchecked")
        //可以找到系统的所有的串口，每个串口对应一个CommPortldentifier
        Enumeration<CommPortIdentifier> portList = CommPortIdentifier.getPortIdentifiers();
        return portList;
    }

    /**
     * 获取指定的类型的端口
     * @param portType 端口类型
     * @return
     */
    public static List<CommPortIdentifier> listAllPort(int portType) {
        List<CommPortIdentifier> ret = new ArrayList<>();
        Enumeration<CommPortIdentifier> all = listAllPort();
        if (all == null)
            return ret;
        //测试此枚举是否包含更多的元素
        while (all.hasMoreElements()) {
            //如果此枚举对象至少还有一个可提供的元素，则返回此枚举的下一个元素（也就是下一个串口）
            CommPortIdentifier portId = (CommPortIdentifier) all.nextElement();
            //检测端口类型是否为指定的串口类型，这里我们只是获取指定的串口类型
            if (portId.getPortType() == portType) {
                ret.add(portId);
            }
        }
        return ret;
    }

    /**
     * 返回所有的串口（CommPortIdentifier.PORT_SERIAL表示串行端口，是一个整形类型1，还有其它的类型，比如CommPortIdentifier.PORT_PARALLEL表示并行串口）
     * @return
     */
    public static List<CommPortIdentifier> listAllSerialPort() {
        return listAllPort(CommPortIdentifier.PORT_SERIAL);
    }

    /**
     * 获取当前连接的串口，例如当前使用的是COM6，那么传入到portName就是COM6
     * @param portName 串口名称（传入计算机的串口名称，根据自己的USB接的串口传入）
     * @return
     */
    public static CommPortIdentifier getSerialPort(String portName) {
        List<CommPortIdentifier> list = listAllSerialPort();
        if (list == null)
            return null;
        //找到对应的串口，并返回
        for (CommPortIdentifier p : list) {
            if (p.getName().equalsIgnoreCase(portName)) {
                return p;
            }
        }
        return null;
    }
    // 串口监听器实现类
    public static class SerialPortClient implements SerialPortEventListener {
        /**
         * CommPortIdentifier类和CommPort类有什么区别呢，其实它们两者是一一对应的关系。
         * CommPortIdentifier主要负责端口的初始化和开启，以及管理它们的占有权。
         * 而CommPort则是跟实际的输入和输出功能有关的。
         * 通过CommPort的getInputStream()可以取得端口的输入流，它是java.io.InputStream接口的一个实例。
         */
        public final CommPortIdentifier port;
        /**
         * SerialPort类是CommPort的子类
         */
        public SerialPort serialPort;
        /**
         * 输入输出流
         */
        public InputStream is;
        public OutputStream os;

        public SerialPortClient(CommPortIdentifier port) {
            this.port = port;
        }

        /**
         * 初始化并打开串口
         * @throws IOException
         * @throws UnsupportedCommOperationException
         * @throws TooManyListenersException
         * @throws PortInUseException
         * @throws gnu.io.PortInUseException
         */
        public void initAndOpen() throws IOException, UnsupportedCommOperationException,
                TooManyListenersException, PortInUseException, gnu.io.PortInUseException {
            System.out.println("串口：初始化和打开。。。（波特率9600，数据位8，停止位1，无奇偶校验）");
            //在使用前，必须先打开串口，open方法有两个参数，第一个是String，通常设置为你的应用程序的名字（可以是任意的名称）。
            // 第二个参数是时间，即开启端口超时的毫秒数。当端口被另外的应用程序占用时，将抛出PortInUseException异常。
            serialPort = (SerialPort)port.open("SerialPort-Test", 2000);
            //向SerialPort对象中添加串口事件监听器
            serialPort.addEventListener(this);
            //设置串口有数据的事件true有效,false无效
            serialPort.notifyOnDataAvailable(true);
            //设置串口参数依次为(波特率,数据位,停止位,奇偶检验)
            serialPort.setSerialPortParams(9600,
                    SerialPort.DATABITS_8,
                    SerialPort.STOPBITS_1,
                    SerialPort.PARITY_NONE);
            //获取串口的输入流和输出流
            os = serialPort.getOutputStream();
            is = serialPort.getInputStream();
        }

        /**
         * 关闭端口的方法，使用完的端口，必须记得将其关闭，这样可以让其它的程序有机会使用它，不然其它程序使用该端口时可能会抛出端口正在使用中的错误。
         * 很奇怪的是，CommPortIdentifier类只提供了开启端口的方法，而要关闭端口，则要调用CommPort类的close()方法。
         */
        public void close() {
            System.out.println("串口：关闭。。。");
            if (os != null)
                try {
                    os.close();
                } catch (IOException e) {
                }
            if (is != null)
                try {
                    is.close();
                } catch (IOException e) {
                }
            if (serialPort != null)
                serialPort.close();
        }
        /**
         * 向串口发送数据
         * @param arr
         * @throws IOException
         */
        public void send(byte[] arr) throws IOException {
            if (arr != null && os != null) {
                System.out.println("串口：发送字节个数" + arr.length);
                os.write(arr);
            } else {
                System.out.println("串口：发送失败，发送数据为空或没打开串口");
            }
        }

        /**
         * 从串口中读取数据
         * @param event
         */
        protected void onReceive(SerialPortEvent event) {
            System.out.println("串口：读数据。。。");
            int newData = 0;
            do {
                try {
                    newData = is.read();
                    System.out.println("串口：读到数据：" + Integer.toString(newData, 16));
                } catch (IOException e) {
                    return;
                }
            } while (newData != -1);
        }

        public void serialEvent(SerialPortEvent event) {
            switch (event.getEventType()) {
                case SerialPortEvent.BI:/*Break interrupt,通讯中断*/
                case SerialPortEvent.OE:/*Overrun error，溢位错误*/
                case SerialPortEvent.FE:/*Framing error，传帧错误*/
                case SerialPortEvent.PE:/*Parity error，校验错误*/
                case SerialPortEvent.CD:/*Carrier detect，载波检测*/
                case SerialPortEvent.CTS:/*Clear to send，清除发送*/
                case SerialPortEvent.DSR:/*Data set ready，数据设备就绪*/
                case SerialPortEvent.RI:/*Ring indicator，响铃指示*/
                case SerialPortEvent.OUTPUT_BUFFER_EMPTY:/*Output buffer is empty，输出缓冲区清空*/
                    break;
                case SerialPortEvent.DATA_AVAILABLE:/*Data available at the serial port，端口有可用数据。读到缓冲数组，输出到终端*/
                    onReceive(event);
                    break;
                default:
                    break;
            }
        }
    }
}
