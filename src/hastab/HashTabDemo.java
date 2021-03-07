package hastab;

import java.util.Scanner;

public class HashTabDemo {

	public static void main(String[] args) {

		HashTab hashTab = new HashTab(7);
		
		// 写一个简单菜单
		String key = "";
		Scanner scanner = new Scanner(System.in);
        while(true) {
			System.out.println("add: 添加雇员");
			System.out.println("list: 显示雇员");
			System.out.println("find: 查找雇员");
			System.out.println("exit: 退出");
			
			key = scanner.next();
			
			switch(key){
			case "add":
				System.out.println("输入id:");
				int id = scanner.nextInt();
				System.out.println("输入姓名： ");
				String name = scanner.next();
				// 创建雇员
				Emp emp = new Emp(id, name);
				hashTab.add(emp);
				break;
			case "list":
				hashTab.lsit();
				break;
			case "find":
				System.out.println("输入要查找的id： ");
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

// 雇员信息
class Emp {
	public int id;
	public String name;
	Emp next;

	// 构造器
	public Emp(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
}

// 一条链表
class EmpLinkedList {

	private Emp head; // 头指针，指向第一个雇员Emp
	
	
	// 添加雇员
	// 1、假定添加时，id自增长，即id从小到大
	// 2、直接加到本链表的最后即可
	public void add(Emp emp) {
		// 如果是添加第一个雇员
		if (head == null) {
			head = emp;
			return;
		} else {
			// 使用辅助指针，帮助定位到最后
			Emp curEmp = head;
			while (true) {
				if (curEmp.next == null) { // 说明链表到了最后
					break;
				}
				curEmp = curEmp.next; // 后移
			}

			// 退出时，将emp 加入链表
			curEmp.next = emp;
		}
	}

	public void list(int no) {
		if (head == null) {
			System.out.println("第"+(no+1)+"条链表为空！！！");
			return;
		}
		System.out.print("第"+(no+1)+"条链表信息为：");
		Emp curEmp = head; // 辅助指针
		while (true) {
			System.out.printf("=> id=%d name=%s\t", curEmp.id, curEmp.name);
			if (curEmp.next == null) {
				// 到了最后
				break;
			}
			curEmp = curEmp.next; // 后移，遍历
		}
		System.out.println();
	}
	
	// 根据id查找雇员
	// 没有找到的话，就返回空
	public Emp findEmpById(int id) {
		// 判断链表是否为空
		if (head == null) {
			System.out.println("链表为空！");
			return null;
		}
		
		// 辅助指针
		Emp curEmp = head;
		while(true) {
			if (curEmp.id == id) {
				break;
			}if(curEmp.next == null) {   // 说明没找到
				curEmp = null;         // 置空即可
				break;          // !!! 记得退出循环 ， 否则在 查询不到对应 id  的雇员时，会出现空指针异常
			}
			curEmp = curEmp.next;
		}
		return curEmp;
	}
}



// 创建真正的HasTab
class HashTab {
	private EmpLinkedList[] empLinkedListArray;
	private int size; // 表示共有多少条链表

	// 构造器
	public HashTab(int size) {
		// 初始化链表
		this.size = size;
		empLinkedListArray = new EmpLinkedList[size];
		// ？？？？
		// 分别初始化每一条链表
		for (int i =0;i<size;i++) {
			empLinkedListArray[i] = new EmpLinkedList();
		}

	}

	// 真正的操作行为都应该在HashTab上呈现
	// 添加雇员
	public void add(Emp emp) {
		// 根据员工的id得到该员工应该添加到哪条链表
		int empLinkedListNO = hasFun(emp.id);
		// 将emp添加到对应的链表中
		empLinkedListArray[empLinkedListNO].add(emp);      // !! 报错： 空指针错误 ！！
		
		
	}

	// 遍历所有的链表
	public void lsit() {
		for (int i = 0; i < size; i++) {
			// 这个i指的是链表的序号
			empLinkedListArray[i].list(i);
		}
	}
	
	// 根据 id查找雇员
	public void finEmpById(int id) {
		// 使用散列函数，看到哪条链表上查找
		int empLinkedListNO = hasFun(id);
		Emp emp = empLinkedListArray[empLinkedListNO].findEmpById(id);
		if(emp!=null) {
			System.out.printf("在第 x=%d 条链表中找到该雇员 id=%d\n", empLinkedListNO+1, id);
		}else {
			System.out.println("在表中未找到该雇员");
		}
	}

	// 编写散列函数
	// 使用简单的取模法
	public int hasFun(int id) {
		return id % size;
	}

}