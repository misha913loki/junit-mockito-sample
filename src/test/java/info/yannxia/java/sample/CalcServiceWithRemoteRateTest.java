package info.yannxia.java.sample;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

/**
 * Created by yann on 2017/6/13.
 * CalcServiceWithRemoteRate 测试，使用匿名内部类
 */
public class CalcServiceWithRemoteRateTest {

    private CalcServiceWithRemoteRate calcService = new CalcServiceWithRemoteRate(new RateService() {
        @Override
        public BigDecimal currentRate() {
            return new BigDecimal("0.01");
        }

        @Override
        public BigDecimal currentRate(Long userId) {
            if (userId == 0) {
                return new BigDecimal("0.01");
            } else {
                return new BigDecimal("0.02");
            }
        }
    }, 0L);


    @Test
    public void test_compoundingCalc() throws Exception {

        BigDecimal total = calcService.compoundingCalc(new BigDecimal("4000"), 1);
        Assert.assertTrue("total money error", total.compareTo(new BigDecimal("4000")) == 0);

        total = calcService.compoundingCalc(new BigDecimal("4000"), 2);
        Assert.assertTrue("total money error", total.compareTo(new BigDecimal("8040")) == 0);

        total = calcService.compoundingCalc(new BigDecimal("4000"), 4);
        Assert.assertTrue("total money error", total.compareTo(new BigDecimal("16241.604")) == 0);
    }

    @Test
    public void test_compoundingCalcWithUserId() throws Exception {

        BigDecimal total = calcService.compoundingCalcWithUserId(new BigDecimal("4000"), 1, 0L);
        Assert.assertTrue("total money error", total.compareTo(new BigDecimal("4000")) == 0);

        total = calcService.compoundingCalcWithUserId(new BigDecimal("4000"), 2, 0L);
        Assert.assertTrue("total money error", total.compareTo(new BigDecimal("8040")) == 0);

        total = calcService.compoundingCalcWithUserId(new BigDecimal("4000"), 4, 0L);
        Assert.assertTrue("total money error", total.compareTo(new BigDecimal("16241.604")) == 0);
    }

    //这里很难去测试 compoundingCalcWithMagicUserId
}