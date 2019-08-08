package com.aliware.tianchi;


/**
 * @author guohaoice@gmail.com
 */
public interface HashInterface {

    /**
     * 计算给定字符串的 hash 值
     * <li>
     * <ol>接口的响应时间符合负指数分布 </ol>
     * <ol>接口的并发度（允许同时调用的线程数）会随时间增加或减小，从而模拟生产环境可能的排队</ol>
     * </li>
     *
     * @param input 要计算的字符串
     * @return 字符串的 hash 值
     */
    Integer hash(String input);
}
