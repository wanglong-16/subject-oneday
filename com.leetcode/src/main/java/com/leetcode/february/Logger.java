package com.leetcode.february;

import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * 359. 日志速率限制器
 * 请你设计一个日志系统，可以流式接收消息以及它的时间戳。每条 不重复 的消息最多只能每 10 秒打印一次。也就是说，
 * 如果在时间戳 t 打印某条消息，那么相同内容的消息直到时间戳变为 t + 10 之前都不会被打印。
 * 所有消息都按时间顺序发送。多条消息可能到达同一时间戳。
 *
 * 实现 Logger 类：
 *
 * Logger() 初始化 logger 对象
 * bool shouldPrintMessage(int timestamp, string message) 如果这条消息
 * message 在给定的时间戳 timestamp 应该被打印出来，则返回 true ，否则请返回 false 。
 * 示例：
 *
 * 输入：
 * ["Logger", "shouldPrintMessage", "shouldPrintMessage", "shouldPrintMessage",
 * "shouldPrintMessage", "shouldPrintMessage", "shouldPrintMessage"]
 * [[], [1, "foo"], [2, "bar"], [3, "foo"], [8, "bar"], [10, "foo"], [11, "foo"]]
 * 输出：
 * [null, true, true, false, false, false, true]
 *
 * 解释：
 * Logger logger = new Logger();
 * logger.shouldPrintMessage(1, "foo");  // 返回 true ，下一次 "foo" 可以打印的时间戳是
 * 1 + 10 = 11
 * logger.shouldPrintMessage(2, "bar");  // 返回 true ，下一次 "bar" 可以打印的时间戳是
 * 2 + 10 = 12
 * logger.shouldPrintMessage(3, "foo");  // 3 < 11 ，返回 false
 * logger.shouldPrintMessage(8, "bar");  // 8 < 12 ，返回 false
 * logger.shouldPrintMessage(10, "foo"); // 10 < 11 ，返回 false
 * logger.shouldPrintMessage(11, "foo"); // 11 >= 11 ，返回 true ，
 * 下一次 "foo" 可以打印的时间戳是 11 + 10 = 21
 * @version: 1.0
 * @date: 2021-02-07 15:25:16
 * @author: wanglong16@meicai.cn
 */
class Logger {

    /** Initialize your data structure here. */
    public Logger() {

    }

    private Map<String, Integer> msgMap = new HashMap<>(1600);

    /** Returns true if the message should be printed in the given timestamp, otherwise returns false.
     If this method returns false, the message will not be printed.
     The timestamp is in seconds granularity. */
    public boolean shouldPrintMessage(int timestamp, String message) {
        if (msgMap.get(message) != null) {
            int lastTimeStamp = msgMap.get(message);
            if (timestamp - lastTimeStamp < 10) {
                return false;
            }
        }
        msgMap.put(message, timestamp);
        return true;
    }
}
