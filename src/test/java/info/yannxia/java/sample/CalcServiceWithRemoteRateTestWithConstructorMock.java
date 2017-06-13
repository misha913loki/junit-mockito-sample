package info.yannxia.java.sample;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;

/**
 * Created by yann on 2017/6/13.
 * CalcServiceWithRemoteRate 测试，使用mockito
 */
public class CalcServiceWithRemoteRateTestWithConstructorMock {
    private RateService rateService = Mockito.mock(RateService.class);
    private final static Long magicNumber = 1000L;
    private CalcServiceWithRemoteRate calcService = new CalcServiceWithRemoteRate(rateService, magicNumber);


    @Before
    public void setup() {
        Mockito.stub(rateService.currentRate()).toReturn(new BigDecimal("0.01"));

        Mockito.stub(rateService.currentRate(Mockito.anyLong())).toReturn(new BigDecimal("0.02"));
        Mockito.stub(rateService.currentRate(Mockito.eq(0L))).toReturn(new BigDecimal("0.01"));

    }

    //测试是否正确的调用 rateService 的 currentRate
    @Test
    public void test_compoundingCalcWithMagicUserId() throws Exception {
        Long userId = 0L;
        calcService.compoundingCalcWithMagicUserId(new BigDecimal("4000"), 2, userId);
        Mockito.verify(rateService, Mockito.only()).currentRate(Mockito.eq(magicNumber + userId));
    }
}
