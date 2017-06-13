package info.yannxia.java.sample;

import okhttp3.OkHttpClient;
import okhttp3.Request;

import java.io.IOException;
import java.math.BigDecimal;

/**
 * Created by yann on 2017/6/13.
 */
public class RateService {

    private OkHttpClient okHttpClient;

    /**
     * 获得当前利率
     *
     * @return 当前利率
     */
    public BigDecimal currentRate() {
        String res = null;
        try {
            res = okHttpClient.newCall(new Request.Builder().build())
                    .execute().body().string();
        } catch (IOException e) {
            throw new RuntimeException();
        }
        return new BigDecimal(res);
    }

    /**
     * 获得当前利率
     *
     * @param userId 用户ID
     * @return 用户当前利率
     */
    public BigDecimal currentRate(Long userId) {
        String res = null;
        try {
            res = okHttpClient.newCall(new Request.Builder()
                    .addHeader("user_id", String.valueOf(userId))
                    .build())
                    .execute().body().string();
        } catch (IOException e) {
            throw new RuntimeException();
        }
        return new BigDecimal(res);
    }
}
