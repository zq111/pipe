/**
 * 
 */
package org.geek.pipe.core;

/**
 * @author haichuan 2012-2-2
 */
public class ResponseCode {

	// �ɹ�
	public static final ResponseCode SUCCESS = new ResponseCode(200);
	// ʧ��
	public static final ResponseCode FAIL = new ResponseCode(500);
	// �ɹ��ύ����
	public static final ResponseCode SUCCESSCOMMINT = new ResponseCode(201);
	// ʧ������ع�
	public static final ResponseCode FAILROLLBACK = new ResponseCode(501);
	//��֧�ڵ㷵�����1000��ʼ����
	public static final ResponseCode IF = new ResponseCode(1001);
	//�����Ҫelse if ����1003��1004...1xxx
	public static final ResponseCode ELSE = new ResponseCode(1002);

	private int code = 0 << 1;
	private String detail;

	public ResponseCode(int code) {
		this.code = code;
	}

	public ResponseCode(int code, String detail) {
		this.code = code;
		this.detail = detail;
	}

	public ResponseCode() {
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public int getCode() {
		return code;
	}

	public String getDetail() {
		return detail;
	}

	// �ɹ�
	public static final ResponseCode SUCCESS(String detail) {
		return new ResponseCode(200, detail);
	}

	// ʧ��
	public static final ResponseCode FAIL(String detail) {
		return new ResponseCode(500, detail);
	}

	// �ɹ��ύ����
	public static final ResponseCode SUCCESSCOMMINT(String detail) {
		return new ResponseCode(201, detail);
	}

	// ʧ������ع�
	public static final ResponseCode FAILROLLBACK(String detail) {
		return new ResponseCode(501, detail);
	}
}
