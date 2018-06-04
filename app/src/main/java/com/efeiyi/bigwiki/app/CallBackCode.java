package com.efeiyi.bigwiki.app;

/**
 * 错误消息返回
 */
public class CallBackCode {

    public static final String TAG = CallBackCode.class.getSimpleName();


    public static int INNER_ERROR = 1;                      // 内部错误
    public static int PARAMS_ERROR = 2;                    //参数错误
    public static int USER_NOT_LOGIN_ERROR = 3;              //用户没有登录
    public static int INPUT_PHONE_ERROR = 4;    //请输入手机号
    public static int PHONE_USEED_ERROR = 5;    //此手机号已经被占用
    public static int VERIFY_SENDED_ERROR = 6;  //验证码已发送,请稍后再试...
    public static int PHONE_VERIFY_NEWPWD_EMPTY_ERROR = 7; //手机号,验证码,新密码均不能为空
    public static int PHONE_NOT_REGISTER_ERROR = 8; //该手机号没注册
    public static int VERIFY_OVERTIME_ERROR = 9; //验证码超时,请重新获取
    public static int VERIFY_GET_ERR0R = 10; //请先获取验证码
    public static int VERIFY_ERROR = 11;//验证码错误
    public static int USER_PWD_EMPTY_ERROR = 12;//用户名,密码不能为空
    public static int USER_PWD_ERROR = 13;  //用户名,密码错误
    public static int PHONE_NEW_OLD_SAME_ERROR = 14; //新老手机号不能相同
    public static int USER_IS_USEED_ERROR = 15; //用户名已被占用
    public static int USER_WRONGFUL_ERROR = 16; //用户名不合法(字母数字下划线)
    public static int NOT_BIND_ACCOUNT = 17; //未绑定账号
    public static int PWD_ERROR = 18;  //密码错误
    public static int RESOURCE_UPLOAD_ERROR = 19; //资源上传错误
    public static int SQL_ERROR = 20;// 数据库异常操作失败
    public static int PWD_ONE_LETTER_OR_NUMBER_ERROR = 21;  //密码为至少包含一个字母一个数字的6-16位字符串


    private static String[] codeDescription = {
            "", "内部错误", "参数错误", "用户没有登录", "请输入手机号", "此手机号已经被占用",
            "验证码已发送,请稍后再试...", "手机号,验证码,新密码均不能为空", "该手机号没注册",
            "验证码超时,请重新获取", "请先获取验证码", "验证码错误", "用户名,密码不能为空", "用户名,密码错误",
            "新老手机号不能相同", "用户名已被占用", "用户名不合法(字母数字下划线)", "未绑定账号",
            "密码错误", "资源上传错误", "数据库异常操作失败", "密码为至少包含一个字母一个数字的6-16位字符串"
    };

    /**
     * 显示错素信息
     *
     * @param code
     * @return
     */
    public static String showDescription(int code) {
        return codeDescription[code];
    }

}
