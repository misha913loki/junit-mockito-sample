package info.yannxia.java.sample;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;

/**
 * Created by yann on 2017/6/13.
 * CalcServiceWithRemoteRate 测试，使用mockito
 */
public class CalcServiceWithRemoteRateTestWithMock {

    @InjectMocks
    private CalcServiceWithRemoteRate calcService;

    @Mock
    private RateService rateService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        Mockito.stub(rateService.currentRate()).toReturn(new BigDecimal("0.01"));

        Mockito.stub(rateService.currentRate(Mockito.anyLong())).toReturn(new BigDecimal("0.02"));
        Mockito.stub(rateService.currentRate(Mockito.eq(0L))).toReturn(new BigDecimal("0.01"));

    }

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


        total = calcService.compoundingCalcWithUserId(new BigDecimal("4000"), 2, 1L);
        Assert.assertTrue("total money error", total.compareTo(new BigDecimal("8080")) == 0);
    }
}
