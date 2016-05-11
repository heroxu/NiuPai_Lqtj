package com.rxjava_niupai_lqtj;

import android.os.Environment;

import java.io.File;

public class Const {
	//服务器链接地址
	private static final String BASE_URL="http://192.168.0.58:8100";		// 测试环境
	//private static final String BASE_URL="http://www.artden.cn:8100";		// 正式网络
	
	//网页地址
	public static final String URL_USER_PRO = BASE_URL+"/public/user_protocol.html";
	public static final String URL_USER_HELP = BASE_URL+"/public/app_help.html";
	public static final String URL_HOW_CEATE = BASE_URL+"/public/how_cfund.html";
	public static final String URL_SIZE_TABLE = BASE_URL+"/public/artden_size.html";
	public static final String URL_RANDOM_IMG = BASE_URL+"/imglibs/getImgs";
	public static final String URL_SHAREGOODS = "http://artden.cn/mobileweb/goods-detail.html?cfunmdseid=";
	//App初始化、配置项、地址
	public static final String URL_APP_INIT = BASE_URL+"/app/initClient";
	public static final String URL_APP_CHECKUPDATE = BASE_URL+"/app/testUpdate";
	public static final String URL_APP_GETCONFIG = BASE_URL+"/app/getInitConfig";
	public static final String URL_APP_ADDRESS = BASE_URL+"/app/getAddress";
	public static final String URL_USER_FEEDBACK = BASE_URL+"/app/userFeedBack";
	public static final String URL_BUYER_DISCOVER = BASE_URL+"/app/getSpecialSubject";
	//用户登录注册、重置密码
	public static final String URL_USER_LOGIN = BASE_URL+"/user/login";
	public static final String URL_USER_OTHERLOGIN = BASE_URL+"/user/thirdLogin";
	public static final String URL_USER_REGISTE = BASE_URL+"/user/register";
	public static final String URL_USER_GETCODE = BASE_URL+"/user/getVerifyCode";
	public static final String URL_USER_RESETPWD = BASE_URL+"/user/resetPwd";
	//用户信息（账单、资料、修改、填写邀请码）
	public static final String URL_USER_BILLLIST = BASE_URL+"/api/v1/user/getMonthBill";
	public static final String URL_USER_GETINFO = BASE_URL+"/api/v1/user/getUserInfo";
	public static final String URL_USER_MODIFY_HEADE = BASE_URL+"/api/v1/user/setHeadPhoto";
	public static final String URL_USER_MODIFY_NICKNAME = BASE_URL+"/api/v1/user/setNickname";
	public static final String URL_USER_MODIFY_PHONE = BASE_URL+"/api/v1/user/setPhone";
	public static final String URL_USER_MODIFY_EMAIL = BASE_URL+"/api/v1/user/setEmail";
	public static final String URL_USER_MODIFY_BANK = BASE_URL+"/api/v1/user/setBank";
	public static final String URL_USER_MODIFY_ADDRESS = BASE_URL+"/api/v1/user/setAddress";
	public static final String URL_USER_MODIFY_PWD = BASE_URL+"/api/v1/user/setPwd";
	public static final String URL_USER_FILL_INVITECODE = BASE_URL+"/api/v1/user/setInviteCode";
	public static final String URL_USER_ADDRESS = BASE_URL+"/api/v1/user/getUserAddrs";
	//好友列表、作品墙
	public static final String URL_USER_FRIENDS = BASE_URL+"/api/v1/user/getFriends";
	public static final String URL_USER_WALL = BASE_URL+"/api/v1/user/getProductions";
	//发布众筹
	public static final String URL_CFUND_MYLIST = BASE_URL+"/api/v1/cfund/getCFundList";
	public static final String URL_CFUND_DELETE = BASE_URL+"/api/v1/cfund/delCFund";
	public static final String URL_CFUND_RELEASE = BASE_URL+"/api/v1/cfund/releaseCFund";
	public static final String URL_CFUND_GETGOODSKIND = BASE_URL+"/api/v1/cfund/getMdseType";
	public static final String URL_CFUND_UPLOADIMG = BASE_URL+"/api/v1/cfund/uploadMDImg";
	public static final String URL_CFUND_GETLABLE = BASE_URL+"/api/v1/cfund/getLabelList";
	//买家(分类列表、主页列表、详情、收藏)
	public static final String URL_BUYER_TYPELIST = BASE_URL+"/buyers/findMdseType";
	public static final String URL_BUYER_GOODSLIST = BASE_URL+"/buyers/findMdseCfund";
	public static final String URL_BUYER_COLLECT = BASE_URL+"/api/v1/buyers/setCollect";
	public static final String URL_BUYER_COLLECTLIST = BASE_URL+"/api/v1/buyers/getCollectList";
	public static final String URL_BUYER_DETAIL = BASE_URL+"/buyers/getMdseDetails";

	//购物（购物车列表、添加|修改购物车、删除购物车、现在购买、结算购物车）
	public static final String URL_CART_LIST = BASE_URL+"/api/v1/buyers/getShopCars";
	public static final String URL_CART_ADD = BASE_URL+"/api/v1/buyers/setShopCar";
	public static final String URL_CART_DELETE = BASE_URL+"/api/v1/buyers/delShopCar";
	public static final String URL_CART_DELETEMORE = BASE_URL+"/api/v1/buyers/clearShopCar";

	//订单（生成账单、已支付账单、待支付账单、确认账单、查询订单、删除订单、订单退款、物流）
	public static final String URL_INIT_ORDERS = BASE_URL+"/api/v1/buyers/initOrders";
	public static final String URL_PAY_ORDER = BASE_URL+"/api/v1/buyers/payOrder";
	public static final String URL_CONFIRM_PAY = BASE_URL+"/api/v1/buyers/confirmPay";
	public static final String URL_ORDER_LIST = BASE_URL+"/api/v1/buyers/findOrderList";
	public static final String URL_ORDER_DETAILS = BASE_URL+"/api/v1/buyers/OrderDetails";
	public static final String URL_DELETE_ORDER = BASE_URL+"/api/v1/buyers/handleOrder";
	public static final String URL_ORDER_APPLY_REFUND = BASE_URL+"/api/v1/buyers/refundOrder";
	public static final String URL_ORDER_APPLY_INVOICE = BASE_URL+"/api/v1/buyers/getBill";
	public static final String URL_ORDER_BUY_AGAIN = BASE_URL+"/api/v1/buyers/buyAgain";
	public static final String URL_ORDER_BUY_CONTINUE = BASE_URL+"/api/v1/buyers/payAgain";
	public static final String URL_ORDER_LOGISTIC = BASE_URL+"/api/v1/buyers/getLogistics";

	//消息客服部分
	public static final String URL_SEND_MSG = BASE_URL+"/api/v1/msg/userSendMsg";
	public static final String URL_MSG_LIST = BASE_URL+"/api/v1/msg/findMsgList";
	public static final String URL_UPLOAD_IMG = BASE_URL+"/api/v1/msg/uploadMsgImg";

	// 消息类型
	public static final String MSG_TYPE_TXT = "1001";//普通文本
	public static final String MSG_TYPE_IMAGE = "1002";//图片信息
	public static final String MSG_TYPE_BUSINESS = "1003";//快捷业务

	public static final String USER_TO_SERVER = "1001";
	public static final String SERVER_TO_USER = "1002";

	//网络链接相关参数
	public static final int HTTP_GETDATA_TIMEOUT = 20*1000;
	public static final int HTTP_POSTIMG_TIMEOUT = 30*1000;

	public static final int IMAG_SIZE = 600;//图片的最大值
	public static final int UPLOAD_IMGSIZE = 6;
	public static final int PAGE_COUNT = 10;
	public static final int TIME_INTERVAL = 120;
	public static final String KEY_USER= "FR45Tgafdstf2354";// 保存本地数据的key
	public static final String QQ_APP_ID = "1104890169";
	public static final String QQ_APP_SECRET = "C8baNNre6PGSo1m5";

	public static final String WEIXIN_APP_ID = "wx70c88ed319e62799";
	public static final String WEIXIN_APP_SECRET = "cd7e9986916d8a1747a363786ef90c38";

	public static final String SINA_APP_ID = "3851420176";
	public static final String SINA_APP_SECRET = "9967da7a9fb59fad555485a9ca72912b";
	public static final String AVIARY_CLIENT_ID = "9e24f8747cfe4c67a1a24b6b7491adef";
	public static final String AVIARY_CLIENT_SECRET = "b4b9a57d-e685-4685-a47f-04b6f0502456";
	//无网络错误码
	public static final String ERROR_NO_NET = "-313";//成功
	public static final String RETURN_OK = "1";
	public static final String ERROR_OUTLOGIN = "-1015";
	
	//sharedprefrence
	public static final String SHAREDNAME_APP = "micro_general";
	public static final String SHAREDNAME_USER = "micro_user";
	//不分用户的shared临时变量
	public static final String APP_VERSION = "app_version";
	public static final String APP_ISUPDATE = "isupdate";
	public static final String APP_UPDATEINFO = "updateinfo";
	public static final String APP_UPDATEURL = "updateurl";
	public static final String APP_ADDR_VERSION = "addr_version";
	
	//省市县县级联动的数据保存
	public static final String APP_ADDRESS = "app_address";
	
	//分用户的shared临时变量，切换账户时需要清空
	public static final String USER_AUTOLOGIN = "user_autologin";//是否需要自动登录
	public static final String USER_ACCOUNT = "user_account";//手机号
	public static final String USER_PWD = "user_pwd";//密码
	public static final String USER_TOKEN = "user_token";//用户token
	public static final String USER_ID = "user_id";//用户ID
	public static final String USER_NICKNAME = "user_nickname";//用户昵称
	public static final String USER_FACEURL = "user_faceurl";//用户头像
	public static final String USER_UNREADMSGCOUNT = "user_jpunshMsgCount";//用户未读消息数
	public static final String USER_VIEW_TYPE = "user_viewtype";//用户视图模式
	
	//存储路径
	public static final String savePath = Environment
			.getExternalStorageDirectory().getAbsolutePath()
			+ File.separatorChar + "microfrofit" + File.separatorChar;
	public static final String imgSavePath = savePath + "img/";
	public static final String tackphotoSavePath = savePath + "camera/";
	public static final String direc = savePath + "apk";
	public static final String apkPath = direc + File.separatorChar + "artden.apk";//更新文件存放的位置
	
	
	//广播Action
	public static final String BROAD_LINKSERVER = "linkserver_broadcast";
	public static final String BROAD_UPDATEMYDRAFT = "updatemydraft_broadcast";
	public static final String BROAD_UPDATEMSGLIST = "updatemsglist_broadcast";

	//下载进度
	public static int  loading_process ;//更新

	//notify对应的ID
	public static int NOTIFY_UPDATE_ING = 1;
	public static int NOTIFY_UPDATE_DONE = 3;
	public static int NOTIFY_JPUSH_MSG = 4;
}
