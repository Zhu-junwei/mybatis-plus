package com.zjw;

import com.baomidou.mybatisplus.core.toolkit.AES;
import org.junit.jupiter.api.Test;

/**
 * @author 朱俊伟
 * @since 2024/03/02 23:57
 */
public class AESTest {

    @Test
    public void testAES() {
        // 生成 16 位随机 AES 密钥
        String randomKey = AES.generateRandomKey();
        System.out.println("randomKey = " + randomKey);

        String userName = "root";
        String password = "123456";
        // 随机密钥加密
        String encryptUserName = AES.encrypt(userName, randomKey);
        String encryptPassword = AES.encrypt(password, randomKey);
        System.out.println("encryptUserName = " + encryptUserName);
        System.out.println("encryptPassword = " + encryptPassword);
        // 解密
        String decryptUserName = AES.decrypt(encryptUserName, randomKey);
        String decryptPassword = AES.decrypt(encryptPassword, randomKey);
        System.out.println("decryptUserName = " + decryptUserName);
        System.out.println("decryptPassword = " + decryptPassword);
    }
}
