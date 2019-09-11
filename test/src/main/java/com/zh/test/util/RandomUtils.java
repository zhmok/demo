package com.zh.test.util;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Random;

/**
 * 普通随机数生成工具
 * 一般情况足够使用
 * <p>
 * 在少量的情况下，对随机信息安全要求不高的情况
 * 不能在对随机信息要求高的情况下使用 如 随机生成 彩票 抽奖等
 *
 * <pre>
 * 随机数越安全 速度越慢
 * ThreadLocalRandom.current()                  线程安全 但是不要在线程池的情况下使用 大量随机去情况 线程竞争锁导致性能下降
 * Random                                       随机数 伪随机 可预测 也就是有规则的随机
 * SecureRandom.getInstance("SHA1PRNG", "SUN")  安全随机数  使用算法 加密的强随机数生成器
 * </pre>
 * 安全
 * 生成的位数尽量在64位 继承并重写 {@link Random#next(int)}
 * <p>
 * 产生高强度的随机数，有两个重要的因素：种子和算法
 * SecureRandom 和 Random 都是，也是如果种子一样，产生的随机数也一样：因为种子确定，随机数算法也确定，因此输出是确定的。
 * 只是说，SecureRandom 类收集了一些随机事件，比如鼠标点击，键盘点击等等，SecureRandom 使用这些随机事件作为种子。
 * 这意味着，种子是不可预测的，而不像 Random 默认使用系统当前时间作为种子，有规律可寻
 * 如果自己设置种子的话：推荐 当前系统的 内存 cpu周期 I/O数 等 一般的不可预测随机也是这样做的
 * <p>
 * NativePRNG
 * NativePRNGBlocking
 * NativePRNGNonBlocking
 * PKCS11
 * SHA1PRNG
 * Windows-PRNG
 */
public final class RandomUtils {
    private RandomUtils() {
    }


    /**
     * 数字格式化
     * <p>
     * NumberFormat
     * //创建 一个整数格式 地区用系统默认的
     * NumberFormat integerNumber = NumberFormat.getIntegerInstance(Locale.getDefault());
     * <p>
     * 使用getInstance或getNumberInstance获取正常的数字格式。
     * 使用getIntegerInstance得到的整数格式。
     * 使用getCurrencyInstance来获取货币数字格式。
     * 使用getPercentInstance获取显示百分比的格式。
     * new DecimalFormat("#.00")
     * <pre>
     * NumberFormat numberFormat = NumberFormat.getInstance(Locale.getDefault());
     * //整数部分不会每隔三个，就会有 " ,"
     * numberFormat.setGroupingUsed(false);
     * //线程安全的字符串缓冲类
     * StringBuffer stringBuffer = new StringBuffer();
     * //构造参数 是Format子类里面的 自己特有的参数，传入就行
     * //构造 小数部分的，所以开始 beginIndex（）是从小数点 后面算的，  但是0是从整个格式化数字，第一个算起， 包括 之间用于分组的 " ,"
     * FieldPosition fieldPosition = new FieldPosition(NumberFormat.FRACTION_FIELD);
     * stringBuffer = numberFormat.format(1234.56789, stringBuffer, fieldPosition);
     * System.out.println(stringBuffer.toString());
     * //小数部分， 所以 从5 开始
     * System.out.println(fieldPosition.getBeginIndex() + "   " + fieldPosition.getEndIndex());
     * //切割字符串
     * System.out.println(stringBuffer.toString().substring(fieldPosition.getBeginIndex()));
     * </pre>
     *
     * <pre>
     * //强制转换成DecimalFormat
     * numberDecimalFormat = (DecimalFormat) numberFormat;
     * //保留小数点后面三位，不足的补零,前面整数部分 每隔四位 ，用 “,” 符合隔开
     * numberDecimalFormat.applyPattern("#,####.000");
     * //设置舍入模式 为DOWN,否则默认的是HALF_EVEN
     * numberDecimalFormat.setRoundingMode(RoundingMode.DOWN);
     * //设置 要格式化的数 是正数的时候。前面加前缀
     * numberDecimalFormat.setPositivePrefix("Prefix  ");
     * System.out.println("正数前缀  "+numberDecimalFormat.format(123456.7891));
     * //设置 要格式化的数 是正数的时候。后面加后缀
     * numberDecimalFormat.setPositiveSuffix("  Suffix");
     * System.out.println("正数后缀  "+numberDecimalFormat.format(123456.7891));
     * //设置整数部分的最大位数
     * numberDecimalFormat.setMaximumIntegerDigits(3);
     * System.out.println("整数最大位数 "+numberDecimalFormat.format(123456.7891));
     * //设置整数部分最小位数
     * numberDecimalFormat.setMinimumIntegerDigits(10);
     * System.out.println("整数最小位数 "+numberDecimalFormat.format(123456.7891));
     * //设置小数部分的最大位数
     * numberDecimalFormat.setMaximumFractionDigits(2);
     * System.out.println("小数部分最大位数 "+numberDecimalFormat.format(123.4));
     * //设置小数部分的最小位数
     * numberDecimalFormat.setMinimumFractionDigits(6);
     * System.out.println("小数部分最小位数 "+numberDecimalFormat.format(123.4));
     *
     *
     * </pre>
     * <img src="../../../../../resources/20190506145233442.png" />
     * <p>
     * 123456.789  ###,###.###  123,456.789
     * 123456.789  ###.##  123456.79
     * 123.78  000000.000  000123.780
     * 12345.67  $###,###.###  $12,345.67
     */
    private final static NumberFormat mf = NumberFormat.getInstance(Locale.getDefault());
    private final static Random rd = new Random();
    private final static String s = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private final static char[] c = {
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'
    };
    /**
     * 63
     * 随机数 时使用
     */
    private static final int ch = 0x3f;

    static {
        // 数字不带逗号
        mf.setGroupingUsed(false);
    }

    public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchProviderException {

//        System.out.println(getString(1));
        for (int i = 0; i < 100; i++) {

            System.out.println(mf.format(getDouble() + getInt(20)));
        }


    }

    /**
     * 随机字符串
     *
     * @param len 长度
     * @return
     */
    public static String getString(int len) {
        StringBuilder str = new StringBuilder(Math.max(0, len));
        for (int i = 0; i < Math.max(0, len); i++) {
            str.append(RandomUtils.c[Math.min(rd.nextInt() & ch, c.length - 1)]);
        }
        return str.toString();
    }


    /**
     * number for [0,between)
     *
     * @param between
     * @return
     */
    public static int getInt(int between) {
        return rd.nextInt(between);
    }

    public static double getDouble(){
        return rd.nextDouble();
    }

}
