import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.table.*;
import javax.swing.border.*;
class StopWatch implements ActionListener
{
	private JFrame jf;
	private Container c; 
	private JPanel lower,upper,up1,up2,up2a,up2b;
	private JButton start,stop,split;
	public static int hr,min,sec,count,countno; 
	public static JLabel hour,minute,second;
	private JTable tb;
	private DefaultTableModel dm;
	private JScrollPane sp; 
	StopWatch()
	{
		jf=new JFrame("MyStopWatch");
		jf.setBounds(300,300,250,350);
		jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		c=jf.getContentPane();
		c.setLayout(new GridLayout(2,2));
		
		lower=new JPanel();
		upper=new JPanel();
		up1=new JPanel();
		up2=new JPanel();
		up2a=new JPanel();
		up2b=new JPanel();
		
		upper.setLayout(new GridLayout(2,2));
		up1.setLayout(new FlowLayout(FlowLayout.CENTER));
		up2.setLayout(new GridLayout(2,2));
		up2a.setLayout(new FlowLayout(FlowLayout.CENTER,50,10));
		up2b.setLayout(new FlowLayout(FlowLayout.CENTER));
		lower.setLayout(new BorderLayout());
		
		hour=new JLabel("00 :");
		minute=new JLabel("00 :");
		second=new JLabel("00");
		hour.setForeground(Color.YELLOW);
		minute.setForeground(Color.YELLOW);
		second.setForeground(Color.YELLOW);
		hour.setFont(new Font("Arial",Font.BOLD,40));
		minute.setFont(new Font("Arial",Font.BOLD,40));
		second.setFont(new Font("Arial",Font.BOLD,40));
		up1.add(hour);
		up1.add(minute);
		up1.add(second);
		up1.setBorder(new LineBorder(Color.RED,10,true));
		up1.setBackground(Color.BLUE);
		up2a.setBackground(Color.BLUE);
		up2b.setBackground(Color.BLUE);
		
		start=new JButton("Start");
		stop=new JButton("Stop");
		split=new JButton("Split");
		start.setFont(new Font("Comic Sans Ms",Font.BOLD,12));
		stop.setFont(new Font("Comic Sans Ms",Font.BOLD,12));
		split.setFont(new Font("Comic Sans Ms",Font.BOLD,12));
		start.setBackground(Color.YELLOW);
		stop.setBackground(Color.YELLOW);
		split.setBackground(Color.YELLOW);
		start.setForeground(Color.BLUE);
		stop.setForeground(Color.BLUE);
		split.setForeground(Color.BLUE);
		
		up2a.add(start);
		up2a.add(stop);
		up2b.add(split);
		up2.add(up2a);
		up2.add(up2b);
		upper.add(up1);
		upper.add(up2);
		
		String columns[]={"S.No.","Current Time"};
		dm=new DefaultTableModel(null,columns);
		tb=new JTable(dm);
		
		tb.setGridColor(Color.BLUE);
		
		sp=new JScrollPane(tb);
		lower.add(sp,BorderLayout.CENTER);
		lower.setBorder(new LineBorder(Color.RED,5));
		tb.setFont(new Font("Arial",Font.BOLD,15));
		tb.setForeground(Color.BLUE);
		c.add(upper);
		c.add(lower);
		
		start.addActionListener(this);
		stop.addActionListener(this);
		split.addActionListener(this);
		
		stop.setEnabled(false);
		split.setEnabled(false);

		jf.setResizable(false);
		jf.setVisible(true);
	}
	public void actionPerformed(ActionEvent e)
	{
		Object o=e.getSource();
		if(o==start)
		{
			stop.setEnabled(true);
			split.setEnabled(true);
			start.setEnabled(false);
			count=1;
			hour.setEnabled(true);
			minute.setEnabled(true);
			second.setEnabled(true);
			if(countno!=0)
			{
				for(int i=countno-1;i>=0;i--)
				{
					dm.removeRow(i);
				}
				countno=0;
			}
			Time t=new Time();
			Thread a=new Thread(t);
			a.start();
		}
		else if(o==stop)
		{
			count=0;
			hour.setEnabled(false);
			minute.setEnabled(false);
			second.setEnabled(false);
			split.setEnabled(false);
			stop.setEnabled(false);
			start.setEnabled(true);
		}
		else if(o==split)
		{
			countno++;
			String time=hour.getText()+minute.getText()+second.getText();
			Object ob[]={countno,time};
			dm.insertRow(0,ob);
		}
	}
	public static void main(String args[])
	{
		StopWatch sw=new StopWatch();
	}
	public static boolean count()
	{
		if(count==1)
			return true;
		else
			return false;
	
	}
}
class Time implements Runnable
{
	public void run()
	{
		StopWatch.hr=0;
		StopWatch.hour.setText("00 :");
		StopWatch.minute.setText("00 :");
		StopWatch.second.setText("00");
		
		while(StopWatch.count())
		{
			StopWatch.min=0;
			while(StopWatch.min<60 && StopWatch.count())
			{
				StopWatch.sec=0;
				while(StopWatch.sec<60 && StopWatch.count())
				{
					StopWatch.sec++;
					if(StopWatch.sec<10)
					{
						StopWatch.second.setText("0"+StopWatch.sec);
					}
					else
					{
						StopWatch.second.setText(""+StopWatch.sec);
					}
					
					if(StopWatch.count()==false)
					{
						StopWatch.hour.setText("00 :");
						StopWatch.minute.setText("00 :");
						StopWatch.second.setText("00");
					}
					try
					{
						Thread.sleep(100);
					}
					catch(InterruptedException e)
					{
					}
				}
				StopWatch.min++;
				if(StopWatch.min<10)
				{
					StopWatch.minute.setText("0"+StopWatch.min+" :");
				}
				else
				{
					StopWatch.minute.setText(StopWatch.min+" :");
				}
				
				if(StopWatch.count()==false)
				{
					StopWatch.hour.setText("00 :");
					StopWatch.minute.setText("00 :");
					StopWatch.second.setText("00");
				}
				try
				{
					Thread.sleep(100);
				}
				catch(InterruptedException e)
				{
				}
			}
			StopWatch.hr++;
			if(StopWatch.hr<10)
			{
				StopWatch.hour.setText("0"+StopWatch.hr+" :");
			}
			else
			{
				StopWatch.hour.setText(StopWatch.hr+" :");
			}
			if(StopWatch.count()==false)
			{
				StopWatch.hour.setText("00 :");
				StopWatch.minute.setText("00 :");
				StopWatch.second.setText("00");
			}
		}
	}
}