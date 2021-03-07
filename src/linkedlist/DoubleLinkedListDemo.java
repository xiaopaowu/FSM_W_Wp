package linkedlist;

public class DoubleLinkedListDemo {

	public static void main(String[] args) {
		 //测试一把
		System.out.println("--------双向链表的测试---------");
		//先创建节点
		HeroNode2 hero1 = new HeroNode2(1, "宋江", "及时雨");
		HeroNode2 hero2 = new HeroNode2(2, "卢俊义", "玉麒麟");
		HeroNode2 hero3 = new HeroNode2(3, "吴用", "智多星");
		HeroNode2 hero4 = new HeroNode2(4, "林冲", "豹子头");

		//创建一个双向链表
		DoubleLinkedList doublelinkedlist = new DoubleLinkedList();
		doublelinkedlist.add(hero1);
		doublelinkedlist.add(hero2);
		doublelinkedlist.add(hero3);
		doublelinkedlist.add(hero4);
		
		doublelinkedlist.show();

		
		//修改	
		System.out.println("------------修改后的链表情况------------ "); 
		HeroNode2 newhero4 = new HeroNode2(4, "林小冲", "小豹子头");
		doublelinkedlist.update(newhero4);
		doublelinkedlist.show();
		
		
		//删除
		System.out.println("------------删除英雄后的链表情况------------ "); 
		doublelinkedlist.del(4);
		doublelinkedlist.show();
	}

}


//定义双向链表类，管理英雄
class DoubleLinkedList{
	
	//初始化头节点,不存放具体数据
	private HeroNode2 head = new HeroNode2(0, "", "");
	
	//返回头节点   -- 将private 类型的对象取出来
	public HeroNode2 getHead(){
		return head;
	}
	
	
	
	//从双向链表中删除某个节点  
	//  --- 区别于单向链表--- 可以实现自我删除，不需要依赖辅助节点
	public void del(int no) {
		//判断当前链表是否为空
		if (head.next == null) {
			System.out.println("链表为空，不能删除");
			return;
		}
		
		HeroNode2 temp = head.next;   //指向head.next (单链表中，temp指向head)
		boolean flag = false;
		while(true) {
			if (temp == null) {
				break;
			}
			if(temp.no == no) {
				flag = true;     //找到了
				break;
			}
			temp = temp.next;
		}
		//开始删除
		if(flag) {
			temp.pre.next = temp.next;
			
			//若temp为最后一个节点，该句不成立
			//所以如果是最后一个节点，就不执行这句话，否则出现空指针
			if (temp.next != null) {       
			temp.next.pre = temp.pre;     
			                              
			}
		}else {
			System.out.printf("不存在编号为%d的英雄\n", no);
		}
		
	}
	
	
	
	
	//修改一个节点的内容 (和单向链表一样)
	public void update(HeroNode2 newHeroNode2) {
		//判断是否为空
		if(head.next == null) {
			System.out.println("--------链表为空---------");
			return;
		}
		//找到需要修改的节点，根据no编号
		HeroNode2 temp = head;
		boolean flag = false;      //用来标记是否匹配到
		while(true) {
			if (temp.next == null) {
				break;    //遍历完成，没有该节点
			}
			if (temp.next.no == newHeroNode2.no) {
				flag = true;     //表示已经找到
				break;
			}
			temp = temp.next;    //我忘了这一句，无法完成遍历
		}
		//开始修改，根据flag状态判断
		if(flag) {
			temp.next.name = newHeroNode2.name;
			temp.next.nickname = newHeroNode2.nickname;
		}else {
			System.out.println("--------需要修改的人物不存在---------");
			System.out.printf("没有找到编号为%d的节点\n", newHeroNode2.no);
		}
	}
	
	
	
	
	
	
	//添加节点到双向链表的最后
	public void add(HeroNode2 heroNode) {
		//依旧需要辅助节点
		HeroNode2 temp = head;
		//遍历链表，找到最后
		while (true) {
			if (temp.next == null) {
				break;
			}
			temp = temp.next;
		}
		//当退出while循环时，temp就指向链表的最后
		//将这个节点的next指向 新的节点
		temp.next = heroNode;
		heroNode.pre = temp;     //相对于单项链表，多了这一句
	}
	
	
	//遍历双向链表
	public void show() {
		//判断链表是否为空
		if(head.next == null) {
			System.out.println("--------链表为空---------");
			return;
		}
		//头节点一般不能动，要使用辅助节点
		HeroNode2 temp = head.next;
		while(true) {
			//判断链表是否为空     !!不能写成temp.next，否则会少输出一个
			if (temp == null) {   //已经到达最后一个节点，最后一个节点不输出
				break;
			}
			//否则输出节点信息
			System.out.println(temp);
			temp = temp.next ;    //指针后移
		}
	}
}



//定义Hero Node类，类里的每一个对象都是一个节点
class HeroNode2 {
	public int no;
	public String name;
	public String nickname;
	public HeroNode2 next;        //指向下一个节点，默认为null
	public HeroNode2 pre;         //指向前一个节点的指针，默认为null
	//构造器
	public HeroNode2(int no, String name, String nickname) {
		this.no = no;
		this.name = name;
		this.nickname = nickname;
	}
	//为了显示方法，重写toString
	@Override
	public String toString() {
		return "HeroNode [no=" + no + ", name=" + name + ", nickname=" + nickname + "]";
	}
	
	
	
}
