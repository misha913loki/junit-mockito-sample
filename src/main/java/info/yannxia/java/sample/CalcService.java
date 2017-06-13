package info.yannxia.java.sample;

import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Value;

import java.math.BigDecimal;

/**
 * Created by yann on 2017/6/13.
 */
public class CalcService {

    @Value("${monthlyRate}")
    private BigDecimal monthlyRate;


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
            total = total.multiply(monthlyRate.add(BigDecimal.ONE))
                    .add(monthlyPrincipal);
            i++;
        }

        return total;
    }
}
