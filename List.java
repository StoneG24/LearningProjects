/* ***************************************************
 * Stone Gorman
 * 10/17/2022
 * List.java
 *
 * Generic linked list
 *************************************************** */

// the Node class
class Node<T>
{
	private T data;
	private Node<T> link;

	// constructor
	public Node()
	{
		this.link = null;
	}

	// accessor and mutator for the data component
	public T getData()
	{
		return this.data;
	}

	public void setData(T data)
	{
		this.data = data;
	}

	// accessor and mutator for the link component
	public Node<T> getLink()
	{
		return this.link;
	}

	public void setLink(Node<T> link)
	{
		this.link = link;
	}
}

// the List class
public class List<T>
{
	public static final int MAX_SIZE = 1024;

	public Node<T> head;
	public Node<T> tail;
	public Node<T> curr;
	public int num_items;

	// constructor
	// remember that an empty list has a "size" of -1 and its "position" is at -1
	public List()
	{
		this.head = this.tail = this.curr = null;
		this.num_items = 0;
	}

	// copy constructor
	// clones the list l and sets the last element as the current
	public List(List<T> l)
	{
		Node<T> p = l.head;
		this.head = this.tail = this.curr = null;
		this.num_items = 0;
		for (int i = 0; i < l.GetSize(); i++){
			this.InsertAfter(p.getData());
			p = p.getLink();
		}
	}

	// navigates to the beginning of the list
	public void First()
	{
		this.curr = this.head;
	}

	// navigates to the end of the list
	// the end of the list is at the last valid item in the list
	public void Last()
	{
		this.curr = this.tail;
	}

	// navigates to the specified element (0-index)
	// this should not be possible for an empty list
	// this should not be possible for invalid positions
	public void SetPos(int pos)
	{
		if (!this.IsEmpty() && pos >= 0 && pos < this.GetSize()){
			this.curr = this.head;
			for (int i = 0; i < pos; i++){
				this.curr = this.curr.getLink();
			}
		}
	}

	// navigates to the previous element
	// this should not be possible for an empty list
	// there should be no wrap-around
	public void Prev()
	{
		if (!this.IsEmpty() && this.curr != this.head){
			this.SetPos(this.GetPos() - 1);
		}
	}

	// navigates to the next element
	// this should not be possible for an empty list
	// there should be no wrap-around
	public void Next()
	{
		if (!this.IsEmpty() && this.curr != this.tail){
			this.curr = this.curr.getLink();
		}
	}

	// returns the location of the current element (or -1)
	public int GetPos()
	{
		if (!this.IsEmpty()){
			int i = 1;
			Node<T> p = this.curr;
			while (p != this.tail){
				i++;
				p = p.getLink();
			}
			return (this.GetSize() - i);
		}
		else{
			return -1;
		}
	}

	// returns the value of the current element (or -1)
	public T GetValue()
	{
		if (!IsEmpty()){
			return (curr.getData());
		}
		
		else{
			return null;
		}
	}

	// returns the size of the list
	// size does not imply capacity
	public int GetSize()
	{
		return this.num_items;
	}

	// inserts an item before the current element
	// the new element becomes the current
	// this should not be possible for a full list
	public void InsertBefore(T data)
	{
		if (!this.IsFull()){
			if (this.IsEmpty()){
				this.curr = new Node<T>();
				this.curr.setData(data);
				this.tail = this.curr;
				this.head = this.curr;
				this.num_items++;
			}

			else if (this.curr == this.head){
				this.curr = new Node<T>();
				this.curr.setData(data);
				this.curr.setLink(this.head);
				this.head = this.curr;
				this.num_items++;
			}

			else{
				Node<T> p;
				p = this.curr;
				this.Prev();
				this.curr.setLink(new Node<T>());
				this.curr = this.curr.getLink();
				this.curr.setLink(p);
				this.curr.setData(data);
				this.num_items++;
			}
		}
	}

	// inserts an item after the current element
	// the new element becomes the current
	// this should not be possible for a full list
	public void InsertAfter(T data)
	{
		if (!this.IsFull()){
			if (this.IsEmpty()){
				this.InsertBefore(data);
			}

			else if (this.curr == this.tail){
				this.curr = new Node<T>();
				this.curr.setData(data);
				this.tail.setLink(this.curr);
				this.tail = this.curr;
				this.num_items++;
			}

			else{
				this.Next();
				this.InsertBefore(data);
			}
		}
	}

	// removes the current element (collapsing the list)
	// this should not be possible for an empty list
	public void Remove()
	{
		if (!this.IsEmpty()){
			if (this.curr != this.tail){
				if (this.curr == this.head){
					this.Next();
					this.head = this.curr;
					this.num_items--;
				}

				else{
					this.Prev();
					this.curr.setLink(this.curr.getLink().getLink());
					this.Next();
					this.num_items--;
				}

			}
			else if (this.head == this.tail){
				this.head = this.tail = this.curr = null;
				this.num_items--;
			}


			else{
				this.Prev();
				this.tail = this.curr;
				this.num_items--;
			}
		}
	}

	// replaces the value of the current element with the specified value
	// this should not be possible for an empty list
	public void Replace(T data)
	{
		if (!this.IsEmpty()){
			this.curr.setData(data);
		}
	}

	// returns if the list is empty
	public boolean IsEmpty()
	{
		return (this.head == null);
	}

	// returns if the list is full
	public boolean IsFull()
	{
		return (this.GetSize() == MAX_SIZE);
	}

	// returns if two lists are equal (by value)
	public boolean Equals(List<T> l)
	{
		if (this.GetSize() != l.GetSize()){
			return false;
		}

		Node<T> p, q;
		p = this.head;
		q = l.head;
		for (int i = 0; i < this.GetSize(); i++){
			if (p.getData() != q.getData()){
				return false;
			}
			p = p.getLink();
			q = q.getLink();
		}
		return true;
	}

	// returns the concatenation of two lists
	// l should not be modified
	// l should be concatenated to the end of *this
	// the returned list should not exceed MAX_SIZE elements
	// the last element of the new list is the current
	public List<T> Add(List<T> l)
	{
		List<T> m = new List<T>(this);
		Node<T> p;
		p = l.head;

		for (int i = 0; i < l.GetSize(); i++){
			m.InsertAfter(p.getData());
			p = p.getLink();
		}
		return m;
	}

	// returns a string representation of the entire list (e.g., 1 2 3 4 5)
	// the string "NULL" should be returned for an empty list
	public String toString()
	{
		if (!this.IsEmpty()){
			String s = "";
			Node<T> p;
			p = this.head;

			for (int i = 0; i < this.GetSize(); i++){
				s += p.getData() + " ";
				p = p.getLink();
			}
			return s;
		}

		else{
			return "NULL";
		}
	}
}
