package com.whw.util;

public class StringUtil {

	// 空字符串
	private static final String EMPTY = "";
	// null
	private static final String NULL = null;
	// "set"
	private static final String SET = "set";
	// "get"
	private static final String GET = "get";
	//字符串分隔符
	public static final String SEPARTOR = String.valueOf((char) 29);

	/**
	 * 判断输入的字符串是否为 空 为空的条件: 1.null 2.为 ""
	 *
	 * @param input
	 * @return	是否为空
	 */
	public static boolean isEmpty(String input) {
		return input == null || input.equals(EMPTY);
	}

	/**
	 * 判断输入的字符串是否为 非空
	 *
	 * @param input
	 * @return	是否非空
	 */
	public static boolean isNotEmpty(String input) {
		return !isEmpty(input);
	}

	/**
	 * 判断输入的字符串是否为 空字符串 空字符串的条件: 1.null 2."" 3.全为空格
	 *
	 * @param input
	 * @return	是否为空白
	 */
	public static boolean isBlank(String input) {
		return isEmpty(input) || input.trim().length() == 0;
	}

	/**
	 * 判断输入的字符串是否为 非空字符串
	 *
	 * @param input
	 * @return	是否非空白
	 */
	public static boolean isNotBlank(String input) {
		return !isBlank(input);
	}

	/**
	 * 加强JDK String 的 trim 方法 处理了输入为null的情况
	 *
	 * @param input
	 * @return	去除前后空白的字符串
	 */
	public static String trim(String input) {
		return input == null ? NULL : input.trim();
	}

	/**
	 * 返回输入字符串重复
	 *
	 * @param input
	 * @param count
	 * @return	返回指定字符串
	 */
	public static String repeat(String input, int count) {
		String result = "";
		if (count <= 0) {
			return NULL;
		} else if (count < 25) {
			for (int i = 0; i < count; i++)
				result += input;
		} else {
			StringBuffer sb = new StringBuffer(input.length() * count + 4);
			for (int i = 0; i < count; i++)
				sb.append(input);
			result = sb.toString();
		}
		return result;
	}

	/**
	 * 判断输入的字符串中是否包含空格" " <br>
	 * 输入的字符串为空时 返回false
	 *
	 * @return	是否包含空格
	 */
	public static boolean hasBlank(String input) {
		if (isEmpty(input))
			return false;
		else {
			String temp = input.trim();
			if (temp.length() < input.length())
				return true;
			else
				return input.contains(" ");
		}
	}

	/**
	 * 根据输入的字段名生成对应的set方法的方法名 <br>
	 * 例子: 输入: name 返回: setName
	 *
	 * @param fieldName
	 * @return	set方法名
	 */
	public static String generateSetMethod(String fieldName) {
		if (isBlank(fieldName))
			return EMPTY;
		return SET + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
	}

	/**
	 * 根据输入的字段名生成对应的get方法的方法名 <br>
	 * 例子: 输入: name 返回: getName
	 *
	 * @param fieldName
	 * @return	get方法名
	 */
	public static String generateGetMethod(String fieldName) {
		if (isBlank(fieldName))
			return EMPTY;
		return GET + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
	}

	/**
	 * 根据输入的 set方法名 得到对应的字段名 <br>
	 * 例子: 输入:setName 输出:name
	 *
	 * @param setMethod
	 * @return	字段名
	 */
	public static String getFieldFromSet(String setMethod) {
		if (setMethod.startsWith(SET))
			return setMethod.substring(3, 4).toLowerCase() + setMethod.substring(4);
		else
			return EMPTY;
	}

	/**
	 * 根据输入的 get方法名 得到对应的字段名 <br>
	 * 例子: 输入:getName 输出:name
	 *
	 * @param getMethod
	 * @return	字段名
	 */
	public static String getFieldFromGet(String getMethod) {
		if (getMethod.startsWith(GET))
			return getMethod.substring(3, 4).toLowerCase() + getMethod.substring(4);
		else
			return EMPTY;
	}

	/**
	 * 当给定字符串为null时，转换为Empty
	 *
	 * @param str 被转换的字符串
	 * @return 转换后的字符串
	 */
	public static String nullToEmpty(String str) {
		return str == null ? EMPTY : str;
	}

	/**
	 * 当给定字符串为空字符串时，转换为<code>null</code>
	 *
	 * @param str 被转换的字符串
	 * @return 转换后的字符串
	 */
	public static String emptyToNull(String str) {
		return isEmpty(str) ? null : str;
	}

	/**
	 * 大写首字母<br>
	 * 例如：str = name, return Name
	 *
	 * @param str 字符串
	 * @return 字符串
	 */
	public static String upperFirst(String str) {
		return Character.toUpperCase(str.charAt(0)) + str.substring(1);
	}

	/**
	 * 小写首字母<br>
	 * 例如：str = Name, return name
	 *
	 * @param str 字符串
	 * @return 字符串
	 */
	public static String lowerFirst(String str) {
		return Character.toLowerCase(str.charAt(0)) + str.substring(1);
	}

	/**
	 * 原字符串首字母大写并在其首部添加指定字符串 例如：str=name, preString=get -> return getName
	 *
	 * @param str 被处理的字符串
	 * @param preString 添加的首部
	 * @return 处理后的字符串
	 */
	public static String upperFirstAndAddPre(String str, String preString) {
		if (str == null || preString == null) {
			return null;
		}
		return preString + upperFirst(str);
	}

	/**
	 * 去掉首部指定长度的字符串并将剩余字符串首字母小写<br/>
	 * 例如：str=setName, preLength=3 -> return name
	 *
	 * @param str 被处理的字符串
	 * @param preLength 去掉的长度
	 * @return 处理后的字符串，不符合规范返回null
	 */
	public static String cutPreAndLowerFirst(String str, int preLength) {
		if (str == null) {
			return null;
		}
		if (str.length() > preLength) {
			char first = Character.toLowerCase(str.charAt(preLength));
			if (str.length() > preLength + 1) {
				return first + str.substring(preLength + 1);
			}
			return String.valueOf(first);
		}
		return null;
	}

	/**
	 * 获得set或get方法对应的标准属性名<br/>
	 * 例如：setName 返回 name
	 *
	 * @param getOrSetMethodName
	 * @return 如果是set或get方法名，返回field， 否则null
	 */
	public static String getGeneralField(String getOrSetMethodName) {
		if (getOrSetMethodName.startsWith(GET) || getOrSetMethodName.startsWith(SET)) {
			return cutPreAndLowerFirst(getOrSetMethodName, 3);
		}
		return null;
	}

	/**
	 * 生成set方法名<br/>
	 * 例如：name 返回 setName
	 *
	 * @param fieldName 属性名
	 * @return setXxx
	 */
	public static String genSetter(String fieldName) {
		return upperFirstAndAddPre(fieldName, SET);
	}

	/**
	 * 生成get方法名
	 *
	 * @param fieldName 属性名
	 * @return getXxx
	 */
	public static String genGetter(String fieldName) {
		return upperFirstAndAddPre(fieldName, GET);
	}

	/**
	 * 去掉指定前缀
	 *
	 * @param str 字符串
	 * @param prefix 前缀
	 * @return 切掉后的字符串，若前缀不是 prefix， 返回原字符串
	 */
	public static String removePrefix(String str, String prefix) {
		if (str != null && str.startsWith(prefix)) {
			return str.substring(prefix.length());
		}
		return str;
	}

	/**
	 * 忽略大小写去掉指定前缀
	 *
	 * @param str 字符串
	 * @param prefix 前缀
	 * @return 切掉后的字符串，若前缀不是 prefix， 返回原字符串
	 */
	public static String removePrefixIgnoreCase(String str, String prefix) {
		if (str != null && str.toLowerCase().startsWith(prefix.toLowerCase())) {
			return str.substring(prefix.length());
		}
		return str;
	}

	/**
	 * 去掉指定后缀
	 *
	 * @param str 字符串
	 * @param suffix 后缀
	 * @return 切掉后的字符串，若后缀不是 suffix， 返回原字符串
	 */
	public static String removeSuffix(String str, String suffix) {
		if (str != null && str.endsWith(suffix)) {
			return str.substring(0, str.length() - suffix.length());
		}
		return str;
	}

	/**
	 * 忽略大小写去掉指定后缀
	 *
	 * @param str 字符串
	 * @param suffix 后缀
	 * @return 切掉后的字符串，若后缀不是 suffix， 返回原字符串
	 */
	public static String removeSuffixIgnoreCase(String str, String suffix) {
		if (str != null && str.toLowerCase().endsWith(suffix.toLowerCase())) {
			return str.substring(0, str.length() - suffix.length());
		}
		return str;
	}

	/**
	 * 清理所有空白字符
	 *
	 * @param str 被清理的字符串
	 * @return 清理后的字符串
	 */
	public static String cleanBlank(String str) {
		if (str == null) {
			return null;
		}
		return str.replaceAll("\\s*", EMPTY);
	}

	/**
	 * 补充字符串以满足最小长度 StrUtil.padEnd("1", 3, '0');//"100"
	 *
	 * @param str 字符串
	 * @param minLength 最小长度
	 * @param padChar 补充的字符
	 * @return 补充后的字符串
	 */
	public static String padEnd(String str, int minLength, char padChar) {
		if (str.length() >= minLength) {
			return str;
		}
		StringBuilder sb = new StringBuilder(minLength);
		sb.append(str);
		for (int i = str.length(); i < minLength; i++) {
			sb.append(padChar);
		}
		return sb.toString();
	}
}
