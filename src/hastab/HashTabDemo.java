package hastab;

import java.util.Scanner;

public class HashTabDemo {

	public static void main(String[] args) {

		HashTab hashTab = new HashTab(7);
		
		// дһ���򵥲˵�
		String key = "";
		Scanner scanner = new Scanner(System.in);
        while(true) {
			System.out.println("add: ��ӹ�Ա");
			System.out.println("list: ��ʾ��Ա");
			System.out.println("find: ���ҹ�Ա");
			System.out.println("exit: �˳�");
			
			key = scanner.next();
			
			switch(key){
			case "add":
				System.out.println("����id:");
				int id = scanner.nextInt();
				System.out.println("���������� ");
				String name = scanner.next();
				// ������Ա
				Emp emp = new Emp(id, name);
				hashTab.add(emp);
				break;
			case "list":
				hashTab.lsit();
				break;
			case "find":
				System.out.println("����Ҫ���ҵ�id�� ");
			    id = scanner.nextInt();
			    hashTab.finEmpById(id);
			    break;
			case "exit":
				scanner.close();
				System.exit(0);
			default:
				break;
			}
		}
		
	}

}

// ��Ա��Ϣ
class Emp {
	public int id;
	public String name;
	Emp next;

	// ������
	public Emp(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
}

// һ������
class EmpLinkedList {

	private Emp head; // ͷָ�룬ָ���һ����ԱEmp
	
	
	// ��ӹ�Ա
	// 1���ٶ����ʱ��id����������id��С����
	// 2��ֱ�Ӽӵ����������󼴿�
	public void add(Emp emp) {
		// �������ӵ�һ����Ա
		if (head == null) {
			head = emp;
			return;
		} else {
			// ʹ�ø���ָ�룬������λ�����
			Emp curEmp = head;
			while (true) {
				if (curEmp.next == null) { // ˵�����������
					break;
				}
				curEmp = curEmp.next; // ����
			}

			// �˳�ʱ����emp ��������
			curEmp.next = emp;
		}
	}

	public void list(int no) {
		if (head == null) {
			System.out.println("��"+(no+1)+"������Ϊ�գ�����");
			return;
		}
		System.out.print("��"+(no+1)+"��������ϢΪ��");
		Emp curEmp = head; // ����ָ��
		while (true) {
			System.out.printf("=> id=%d name=%s\t", curEmp.id, curEmp.name);
			if (curEmp.next == null) {
				// �������
				break;
			}
			curEmp = curEmp.next; // ���ƣ�����
		}
		System.out.println();
	}
	
	// ����id���ҹ�Ա
	// û���ҵ��Ļ����ͷ��ؿ�
	public Emp findEmpById(int id) {
		// �ж������Ƿ�Ϊ��
		if (head == null) {
			System.out.println("����Ϊ�գ�");
			return null;
		}
		
		// ����ָ��
		Emp curEmp = head;
		while(true) {
			if (curEmp.id == id) {
				break;
			}if(curEmp.next == null) {   // ˵��û�ҵ�
				curEmp = null;         // �ÿռ���
				break;          // !!! �ǵ��˳�ѭ�� �� ������ ��ѯ������Ӧ id  �Ĺ�Աʱ������ֿ�ָ���쳣
			}
			curEmp = curEmp.next;
		}
		return curEmp;
	}
}



// ����������HasTab
class HashTab {
	private EmpLinkedList[] empLinkedListArray;
	private int size; // ��ʾ���ж���������

	// ������
	public HashTab(int size) {
		// ��ʼ������
		this.size = size;
		empLinkedListArray = new EmpLinkedList[size];
		// ��������
		// �ֱ��ʼ��ÿһ������
		for (int i =0;i<size;i++) {
			empLinkedListArray[i] = new EmpLinkedList();
		}

	}

	// �����Ĳ�����Ϊ��Ӧ����HashTab�ϳ���
	// ��ӹ�Ա
	public void add(Emp emp) {
		// ����Ա����id�õ���Ա��Ӧ����ӵ���������
		int empLinkedListNO = hasFun(emp.id);
		// ��emp��ӵ���Ӧ��������
		empLinkedListArray[empLinkedListNO].add(emp);      // !! ���� ��ָ����� ����
		
		
	}

	// �������е�����
	public void lsit() {
		for (int i = 0; i < size; i++) {
			// ���iָ������������
			empLinkedListArray[i].list(i);
		}
	}
	
	// ���� id���ҹ�Ա
	public void finEmpById(int id) {
		// ʹ��ɢ�к������������������ϲ���
		int empLinkedListNO = hasFun(id);
		Emp emp = empLinkedListArray[empLinkedListNO].findEmpById(id);
		if(emp!=null) {
			System.out.printf("�ڵ� x=%d ���������ҵ��ù�Ա id=%d\n", empLinkedListNO+1, id);
		}else {
			System.out.println("�ڱ���δ�ҵ��ù�Ա");
		}
	}

	// ��дɢ�к���
	// ʹ�ü򵥵�ȡģ��
	public int hasFun(int id) {
		return id % size;
	}

}