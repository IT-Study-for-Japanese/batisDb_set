package egovframework.sample.web;

import egovframework.rte.fdl.cryptography.EgovPasswordEncoder;

public class Main {
	//계정암호화키 키
	public String algorithmKey = "writeYourKeyq";

	//계정암호화 알고리즘(MD5, SHA-1, SHA-256)
	public String algorithm = "SHA-256";

	//계정암호화키 블럭사이즈
	public int algorithmBlockSize = 1024;

	public static void main(String[] args) {
		Main m=new Main();

		EgovPasswordEncoder egovPasswordEncoder = new EgovPasswordEncoder();
		
		egovPasswordEncoder.setAlgorithm(m.algorithm);
		String pw=egovPasswordEncoder.encryptPassword("1234");
		System.out.println("pw:"+pw);
		
		System.out.println("알고리즘(algorithm) : "+m.algorithm);
		System.out.println("알고리즘 키(algorithmKey) : "+m.algorithmKey);
		System.out.println("알고리즘 키 Hash(algorithmKeyHash) : "+egovPasswordEncoder.encryptPassword(m.algorithmKey));
		System.out.println("알고리즘 블럭사이즈(algorithmBlockSize)  :"+m.algorithmBlockSize);
	}

}
