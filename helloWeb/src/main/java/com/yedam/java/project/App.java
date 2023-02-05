package com.yedam.java.project;

import java.io.IOException;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.DataHandler;
import javax.mail.util.ByteArrayDataSource;

public class App {
	// 여기서 메소드 만들어서 MainApp으로 보낸다

	static Scanner sc = new Scanner(System.in);

	static TBL_USER_DAO dao = new TBL_USER_DAO();
	static String send;
	public static void exe() throws IOException {
		first: while (true) {
			System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
			System.out.println("           (주)예담직업전문학교 수강생 관리 프로그램 ");
			System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
			System.out.println("            < 1 > 관리자로그인 < 2 > 프로그램 종료  ");
			System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
			System.out.print("              메뉴 입력  [ 1 ] / [ 2 ] : ");
			int menu = sc.nextInt();
			sc.nextLine();
			System.out.println();

			if (menu == 1) {
				String id;
				String pw;
				while (true) {
					// 로그인 화면
					System.out.println("┌────────────────────────────────────────────────────┐");
					System.out.println("│         (주)예담직업전문학교     관리자      LOGIN         │");
					System.out.println("└────────────────────────────────────────────────────┘");
					System.out.println("                                       접속취소 [취소입력]");
					System.out.print("      아이디  : ");
					id = sc.nextLine();
					if (id.equals("취소")) {
						continue first;
					}
					System.out.print("      비밀번호 : ");
					pw = sc.nextLine();
					if (pw.equals("취소")) {
						continue first;
					}
					System.out.println();
					System.out.println("──────────────────────────────────────────────────────");

					boolean log = dao.login(id, pw);
					if (log == true) {
						System.out.println("                 관리자님 접속을 환영합니다");
						System.out.println("──────────────────────────────────────────────────────");
						System.out.println("\n\n");
						break;
					}
					System.out.println("         [실패] 관리자의 아이디와 비밀번호를 입력해주세요");
				}
				login();
			} else if (menu == 2) {
				System.out.println("──────────────────────────────────────────────────────");
				System.out.println("                 관리자 프로그램을 종료합니다");
				System.out.println("──────────────────────────────────────────────────────");
				break;
			}
		}
	}

	public static void join() {
		loop: while (true) {
			TBL_USER_VO tuv = new TBL_USER_VO();
			System.out.println("──────────────────────────────────────────────────────");
			System.out.println("               (주)예담직업전문학교 수강생 등록");
			System.out.println("──────────────────────────────────────────────────────");
			// 회원가입
			// USERNUMBER NUMBER NOT NULL,
			// ID VARCHAR2(20),
			System.out.print("   아이디  : ");
			String id = sc.nextLine();
			boolean check = dao.checkId(id);
			if (check == true) {
				System.out.println("아이디가 중복됩니다.");
				continue;
			}
			// PW VARCHAR2(20),
			System.out.print("   비밀번호 : ");
			String pw = sc.nextLine();

			// NAME VARCHAR2(20),
			System.out.print("   이  름 : ");
			String name = sc.nextLine();
			// AGE NUMBER,
			System.out.print("   나  이 : ");
			int age = sc.nextInt();
			sc.nextLine();

			// PHONENUMBER VARCHAR2(20)
			System.out.print("   휴대번호 : ");
			String phone = sc.nextLine();

			// SUBJECT VARCHAR2(20)
			System.out.print("   수강과목 : ");
			String sub = sc.nextLine();

			System.out.println("──────────────────────────────────────────────────────");

			tuv.setId(id);
			tuv.setPw(pw);
			tuv.setName(name);
			tuv.setAge(age);
			tuv.setPhoneNumber(phone);
			tuv.setSubject(sub);

			if (dao.join(tuv) > 0) {
				System.out.println("               수강생등록이 완료되었습니다");
				break loop;
			} else {
				System.out.println("               수강생등록이 실패하였습니다.");
			}

		}
	}

	public static void login() throws IOException {

		// 로그인 했을때
		while (true) {
			System.out.println("┌─────────────────────────────────────────────────────┐");
			System.out.println("│                       【7】수강생정보 전송     【9】로그아웃  │");
			System.out.println("│  【1】전체수강생【2】수강생검색【3】수강생등록【4】정보수정【5】정보삭제  │");
			System.out.println("└─────────────────────────────────────────────────────┘");
			System.out.print("        메뉴 입력 : ");
			int stu = sc.nextInt();
			sc.nextLine();
			if (stu == 1) {
				// 수강생 전체 조회
				List<TBL_USER_VO> list = dao.empVolist();
				for (TBL_USER_VO emp : list) {
					System.out.println(emp.toString());
				}

			} else if (stu == 2) {
				// 수강생 단건 조회
				System.out.println("수강생 아이디");
				System.out.print("[검색] ");
				String stuone = sc.nextLine();

				TBL_USER_VO eee = dao.student(stuone);

				if (eee != null) {
					System.out.println(eee.toString());

				} else {
					System.out.println("정보 없음");
				}
			} else if (stu == 3) {
				join();
			} else if (stu == 4) {
				// 수강생정보 수정
				System.out.println("수정할 수강생 아이디");
				System.out.print("[검색] ");
				String upstu = sc.nextLine();

				TBL_USER_VO ddd = dao.student(upstu);

				if (ddd != null) {
					System.out.println(ddd.toString());

					TBL_USER_VO tuv = new TBL_USER_VO();

					System.out.print("아이디확인 : ");
					String upid = sc.nextLine();

					System.out.print("비밀번호 : ");
					String uppw = sc.nextLine();

					System.out.print("휴대번호 : ");
					String upphone = sc.nextLine();

					System.out.print("수강과목 : ");
					String upsub = sc.nextLine();

					tuv.setId(upid);
					tuv.setPw(uppw);
					tuv.setPhoneNumber(upphone);
					tuv.setSubject(upsub);

					if (dao.updEmp(tuv) > 0) {
						System.out.println("수강생등록이 완료되었습니다");
						continue;
					} else {
						System.out.println("수강생등록이 실패하였습니다.");
						continue;
					}

				} else {
					System.out.println("정보 없음");
				}

			} else if (stu == 5) {
				TBL_USER_VO evo = new TBL_USER_VO();
				System.out.println("수강생 아이디");
				System.out.print("[검색] ");
				String delstu = sc.nextLine();
				TBL_USER_VO emp = dao.student(delstu);

				if (emp != null) {
					System.out.println(emp.toString());

					System.out.print("정말 삭제하시겠습니까? Y/N ");
					String pic = sc.nextLine();
					char pic2 = pic.charAt(0);

					if (pic2 == 'Y') {
						evo.setId(delstu);

						if (dao.delEmp(evo) > 0) {
							System.out.println("삭제가 되었습니다.");
							continue;
						} else {
							System.out.println("삭제되지 않았습니다.");
						}
					} else if (pic2 == 'N') {
						System.out.println("삭제가 취소되었습니다.");
						continue;
					}

				} else {
					System.out.println("해당 수강생은 없습니다.");
					continue;
				}

			} else if (stu == 7) {
				System.out.println("메일로 전송할 수강생의 아이디를 입력해주세요 : ");

				send = sc.nextLine();

				TBL_USER_VO emp = dao.student(send);

				if (emp != null) {
					System.out.println(emp.toString());
					Verify();
					System.out.println("현재 수강생의 정보를 메일로 전송합니다.");
				} else {
					System.out.println("정보 없음");
				}

			} else if (stu == 9) {
				System.out.println("로그아웃 하시겠습니까? Y/N ");
				String pic = sc.nextLine();
				char pic2 = pic.charAt(0);
				if (pic2 == 'Y') {
					break;
				} else if (pic2 == 'N') {
					continue;
				}

			}

		}
	}

	public static void Verify() throws IOException {
		TBL_USER_VO tuv = dao.student(send);
		String id = tuv.getId();
		String name = tuv.getName();
		int age = tuv.getAge();
		String phone = tuv.getPhoneNumber();
		String sub = tuv.getSubject();

		String content = 
				  "<html>" 
				+ "<head>" 
				+ "<meta charset='UTF-8' />" 
				+ "<style>"
					+ "div {" 
					+ "border: 1px solid black;" 
					+ "width: 500px;" 
					+ "}"
					+ "h2{text-align: center;}" 
					+ "h3 {" 
						+ "float: left;"
						+ "border-right: 1px solid black;" 
						+ "text-align: center;" 
						+ "width: 170px;"
						+ "line-height: 35px;" 
						+ "height: 35px;"
						+ "margin: 0;" 
					+ "}" 
					+ "ul {list-style: none;}" 
					+ "p {"
						+ "text-align: center;"
						+ "font-size: 20px;" 
						+ "margin: 0;" 
						+ "height: 35px;"
						+ "line-height: 35px;" 
					+ "}" 
				+ "</style>" 
				+ "</head>" 
				+ "<body>"
					+ "<h2>예담직업전문학교 수강생 정보</h2>"
					+ "<div>"
					+ "<h3>ID</h3><p>"+id+"</p> "
					+ "<h3>NAME</h3><p>" + name + "</p>" 
					+ "<h3>AGE</h3><p>"+ age + "</p>" 
					+ "<h3>PHONENUMBER</h3><p>" + phone + "</p>"
					+ "<h3>SUBJECT</h3><p>"+ sub + "</p>"
					+ "</div>"
					+ "</body>"
					+ "</html>";

		String recipient = "choihyunsuk177@gmail.com";

		// 1. 발신자의 메일 계정과 비밀번호 설정
		final String user = "choihyunsuk177@gmail.com";
		final String password = "zyxbucslzyjiigjg";

		// 2. Property에 SMTP 서버 정보 설정
		Properties prop = new Properties();
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", 465);
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.ssl.enable", "true");
		prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		prop.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");

		// 3. SMTP 서버정보와 사용자 정보를 기반으로 Session 클래스의 인스턴스 생성
		Session session = Session.getDefaultInstance(prop, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user, password);
			}
		});

		// 4. Message 클래스의 객체를 사용하여 수신자와 내용, 제목의 메시지를 작성한다.
		// 5. Transport 클래스를 사용하여 작성한 메세지를 전달한다.

		MimeMessage message = new MimeMessage(session);
		try {
			message.setFrom(new InternetAddress(user));

			// 수신자 메일 주소
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));

			// Subject
			message.setSubject("요청하신 수강생의 정보 입니다.");

			// Text
			message.setDataHandler(new DataHandler(new ByteArrayDataSource(content, "text/html;charset=utf-8")));

			Transport.send(message); // send message

		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

}
