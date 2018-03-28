package com.dong1990.logger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 根据需要添加各个不同特性的专用Logger。
 * 一般用法：新增功能/修改时添加一个Logger，在关键位置输出debug级别日志，上线时开启debug级别，便于跟踪查找可能的bug，稳定后，
 * 再把此Logger配置为info级别，关掉debug日志。
 */

public abstract class Loggers {
    public static final Logger TOTAL = LogManager.getLogger("total");
    public static final Logger QUEUE_PRODUCER = LogManager.getLogger("queueProducer");
    public static final Logger HTTP_SEND = LogManager.getLogger("http_send");
    public static final Logger QUERY_BALANCE = LogManager.getLogger("query_balance");
    public static final Logger HTTP_DRAGON = LogManager.getLogger("httpdragon");
    public static final Logger SEND_REP_TO_USER = LogManager.getLogger("send_report_To_user");
    public static final Logger DELIVERY_PUSH = LogManager.getLogger("delivery_push");
    public static final Logger MSG_ERROR = LogManager.getLogger("msg_error");
    public static final Logger BROADCAST = LogManager.getLogger("broadcast");
    public static final Logger FATAL_ERROR = LogManager.getLogger("fatal_error");
    public static final Logger REDIS = LogManager.getLogger("redis");
    public static final Logger WEB = LogManager.getLogger("web");
    public static final Logger RECEIVE_REPORT = LogManager.getLogger("receiveReport");
    public static final Logger GATEWAY_REPORT = LogManager.getLogger("gateway_report");
    public static final Logger TRAFFIC = LogManager.getLogger("gateway_traffic");
    public static final Logger MONITOR = LogManager.getLogger("threadmonitor");
    public static final Logger CMPP_TO_GATEWAY = LogManager.getLogger("cmpp_to_gateway");
    public static final Logger SMGP_FROM_GATEWAY = LogManager.getLogger("smgp_from_gateway");
    public static final Logger HTTP = LogManager.getLogger("http");
    public static final Logger HTTP_BATCH_RESP = LogManager.getLogger("http_batch_resp");
    public static final Logger HTTP_BATCH = LogManager.getLogger("http_batch");
    public static final Logger CMPP_SUBMIT = LogManager.getLogger("cmpp_submit");
    public static final Logger QUEUE_REPORT_MONIT = LogManager.getLogger("queue_report_monitoring");
    public static final Logger UPDATE_FEE = LogManager.getLogger("updateFee");
    public static final Logger DB_MT_MSG = LogManager.getLogger("dbMtMsg");
    public static final Logger ANALYZE_REPORT = LogManager.getLogger("analyze_report");
    public static final Logger SMS_UPDATE = LogManager.getLogger("sms_update");
}
