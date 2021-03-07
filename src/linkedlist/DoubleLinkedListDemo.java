package linkedlist;

public class DoubleLinkedListDemo {

	public static void main(String[] args) {
		 //����һ��
		System.out.println("--------˫������Ĳ���---------");
		//�ȴ����ڵ�
		HeroNode2 hero1 = new HeroNode2(1, "�ν�", "��ʱ��");
		HeroNode2 hero2 = new HeroNode2(2, "¬����", "������");
		HeroNode2 hero3 = new HeroNode2(3, "����", "�Ƕ���");
		HeroNode2 hero4 = new HeroNode2(4, "�ֳ�", "����ͷ");

		//����һ��˫������
		DoubleLinkedList doublelinkedlist = new DoubleLinkedList();
		doublelinkedlist.add(hero1);
		doublelinkedlist.add(hero2);
		doublelinkedlist.add(hero3);
		doublelinkedlist.add(hero4);
		
		doublelinkedlist.show();

		
		//�޸�	
		System.out.println("------------�޸ĺ���������------------ "); 
		HeroNode2 newhero4 = new HeroNode2(4, "��С��", "С����ͷ");
		doublelinkedlist.update(newhero4);
		doublelinkedlist.show();
		
		
		//ɾ��
		System.out.println("------------ɾ��Ӣ�ۺ���������------------ "); 
		doublelinkedlist.del(4);
		doublelinkedlist.show();
	}

}


//����˫�������࣬����Ӣ��
class DoubleLinkedList{
	
	//��ʼ��ͷ�ڵ�,����ž�������
	private HeroNode2 head = new HeroNode2(0, "", "");
	
	//����ͷ�ڵ�   -- ��private ���͵Ķ���ȡ����
	public HeroNode2 getHead(){
		return head;
	}
	
	
	
	//��˫��������ɾ��ĳ���ڵ�  
	//  --- �����ڵ�������--- ����ʵ������ɾ��������Ҫ���������ڵ�
	public void del(int no) {
		//�жϵ�ǰ�����Ƿ�Ϊ��
		if (head.next == null) {
			System.out.println("����Ϊ�գ�����ɾ��");
			return;
		}
		
		HeroNode2 temp = head.next;   //ָ��head.next (�������У�tempָ��head)
		boolean flag = false;
		while(true) {
			if (temp == null) {
				break;
			}
			if(temp.no == no) {
				flag = true;     //�ҵ���
				break;
			}
			temp = temp.next;
		}
		//��ʼɾ��
		if(flag) {
			temp.pre.next = temp.next;
			
			//��tempΪ���һ���ڵ㣬�þ䲻����
			//������������һ���ڵ㣬�Ͳ�ִ����仰��������ֿ�ָ��
			if (temp.next != null) {       
			temp.next.pre = temp.pre;     
			                              
			}
		}else {
			System.out.printf("�����ڱ��Ϊ%d��Ӣ��\n", no);
		}
		
	}
	
	
	
	
	//�޸�һ���ڵ������ (�͵�������һ��)
	public void update(HeroNode2 newHeroNode2) {
		//�ж��Ƿ�Ϊ��
		if(head.next == null) {
			System.out.println("--------����Ϊ��---------");
			return;
		}
		//�ҵ���Ҫ�޸ĵĽڵ㣬����no���
		HeroNode2 temp = head;
		boolean flag = false;      //��������Ƿ�ƥ�䵽
		while(true) {
			if (temp.next == null) {
				break;    //������ɣ�û�иýڵ�
			}
			if (temp.next.no == newHeroNode2.no) {
				flag = true;     //��ʾ�Ѿ��ҵ�
				break;
			}
			temp = temp.next;    //��������һ�䣬�޷���ɱ���
		}
		//��ʼ�޸ģ�����flag״̬�ж�
		if(flag) {
			temp.next.name = newHeroNode2.name;
			temp.next.nickname = newHeroNode2.nickname;
		}else {
			System.out.println("--------��Ҫ�޸ĵ����ﲻ����---------");
			System.out.printf("û���ҵ����Ϊ%d�Ľڵ�\n", newHeroNode2.no);
		}
	}
	
	
	
	
	
	
	//��ӽڵ㵽˫����������
	public void add(HeroNode2 heroNode) {
		//������Ҫ�����ڵ�
		HeroNode2 temp = head;
		//���������ҵ����
		while (true) {
			if (temp.next == null) {
				break;
			}
			temp = temp.next;
		}
		//���˳�whileѭ��ʱ��temp��ָ����������
		//������ڵ��nextָ�� �µĽڵ�
		temp.next = heroNode;
		heroNode.pre = temp;     //����ڵ�������������һ��
	}
	
	
	//����˫������
	public void show() {
		//�ж������Ƿ�Ϊ��
		if(head.next == null) {
			System.out.println("--------����Ϊ��---------");
			return;
		}
		//ͷ�ڵ�һ�㲻�ܶ���Ҫʹ�ø����ڵ�
		HeroNode2 temp = head.next;
		while(true) {
			//�ж������Ƿ�Ϊ��     !!����д��temp.next������������һ��
			if (temp == null) {   //�Ѿ��������һ���ڵ㣬���һ���ڵ㲻���
				break;
			}
			//��������ڵ���Ϣ
			System.out.println(temp);
			temp = temp.next ;    //ָ�����
		}
	}
}



//����Hero Node�࣬�����ÿһ��������һ���ڵ�
class HeroNode2 {
	public int no;
	public String name;
	public String nickname;
	public HeroNode2 next;        //ָ����һ���ڵ㣬Ĭ��Ϊnull
	public HeroNode2 pre;         //ָ��ǰһ���ڵ��ָ�룬Ĭ��Ϊnull
	//������
	public HeroNode2(int no, String name, String nickname) {
		this.no = no;
		this.name = name;
		this.nickname = nickname;
	}
	//Ϊ����ʾ��������дtoString
	@Override
	public String toString() {
		return "HeroNode [no=" + no + ", name=" + name + ", nickname=" + nickname + "]";
	}
	
	
	
}
