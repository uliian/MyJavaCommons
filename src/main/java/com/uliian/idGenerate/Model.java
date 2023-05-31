package com.uliian.idGenerate;

/**
 * ID 生成器服务模式
 * 服务器模式（Server）：默认模式，机器码10位，最大1024，序列21位，每秒最多生成 16,777,216
 * 客户端模式（Client）：客户端模式，机器码21位，最大16,777,216，序列10位，每秒最多生成1024
 */
public enum Model {
    Server,
    Client
}
