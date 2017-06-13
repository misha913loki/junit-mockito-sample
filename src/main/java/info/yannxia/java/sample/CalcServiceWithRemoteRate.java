package info.yannxia.java.sample;

import org.apache.commons.lang3.Validate;

import java.math.BigDecimal;

/**
 * Created by yann on 2017/6/13.
 */
public class CalcServiceWithRemoteRate {

    private final RateService rateService;
    private final Long magicOffsetNumber;

    public CalcServiceWithRemoteRate(RateService rateService, Long magicOffsetNumber) {
        this.rateService = rateService;
        this.magicOffsetNumber = magicOffsetNumber;
    }

    /**
     * 复利计算,每月固定存入本金，计算固定月之后的本息之和
     *
     * @param monthlyPrincipal 每月本金
     * @param month            月数
     * @return 本息之和
     */
    public BigDecimal compoundingCalc(BigDecimal monthlyPrincipal, Integer month) {
        Validate.isTrue(monthlyPrincipal != null && monthlyPrincipal.compareTo(BigDecimal.ZERO) > 0,
                "monthlyPrincipal not null, and must bigger than zero");
        Validate.isTrue(month != null && month > 0,
                "month must bigger than 1");

        BigDecimal total = monthlyPrincipal;
        int i = 1;
        while (i < month) {
            total = total.multiply(rateService.currentRate().add(BigDecimal.ONE))
                    .add(monthlyPrincipal);
            i++;
        }

        return total;
    }


    /**
     * 复利计算,每月固定存入本金，计算固定月之后的本息之和
     *
     * @param monthlyPrincipal 每月本金
     * @param month            月数
     * @return 本息之和
     */
    public BigDecimal compoundingCalcWithUserId(BigDecimal monthlyPrincipal, Integer month, Long userId) {
        Validate.isTrue(monthlyPrincipal != null && monthlyPrincipal.compareTo(BigDecimal.ZERO) > 0,
                "monthlyPrincipal not null, and must bigger than zero");
        Validate.isTrue(month != null && month > 0,
                "month must bigger than 1");

        BigDecimal total = monthlyPrincipal;
        int i = 1;
        while (i < month) {
            total = total.multiply(rateService.currentRate(userId).add(BigDecimal.ONE))
                    .add(monthlyPrincipal);
            i++;
        }

        return total;
    }

    /**
     * 复利计算,每月固定存入本金，计算固定月之后的本息之和
     * 用户ID经过魔数变化
     *
     * @param monthlyPrincipal 每月本金
     * @param month            月数
     * @return 本息之和
     */
    public BigDecimal compoundingCalcWithMagicUserId(BigDecimal monthlyPrincipal, Integer month, Long userId) {
        Validate.isTrue(monthlyPrincipal != null && monthlyPrincipal.compareTo(BigDecimal.ZERO) > 0,
                "monthlyPrincipal not null, and must bigger than zero");
        Validate.isTrue(month != null && month > 0,
                "month must bigger than 1");

        BigDecimal total = monthlyPrincipal;
        int i = 1;
        while (i < month) {
            total = total.multiply(rateService.currentRate(userId + magicOffsetNumber).add(BigDecimal.ONE))
                    .add(monthlyPrincipal);
            i++;
        }

        return total;
    }


}
