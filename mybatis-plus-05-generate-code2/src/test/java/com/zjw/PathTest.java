package com.zjw;

import org.junit.jupiter.api.Test;

/**
 * @author 朱俊伟
 * @since 2024/03/02 1:46
 */
public class PathTest {

    @Test
    public void test() {
        // 使用 getClass().getPackage().getName()

        String moduleName = getClass().getPackage().getName();
        System.out.println("moduleName = " + moduleName);
        moduleName = moduleName.substring(0, moduleName.lastIndexOf("."));
        System.out.println("moduleName = " + moduleName);

    }
}
