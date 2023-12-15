import java.awt.*;
import java.awt.event.*;

public class CalcMachine extends Frame implements ActionListener {
  // ■ フィールド変数
  Button bt0, btpl,btmn,btx,btdv,bteq, btdt,btClr,btmp,btmr,btrou;
  Button[] bt = new Button[10];
  TextField txt1;
  String ss = "";
  String ss2 = "";
  double t1,t2,result=0;
  double memory=0;
  int k ;
  
  public static void main(String [] args) {
    CalcMachine si = new CalcMachine(); 
  }

  // ■ コンストラクタ
  CalcMachine() {
    super("Layout Test");
    setSize(600, 300); 

    //============================================================
    // まず、BorderLayout を設定し、NORTH,CENTER,SOUTHだけを使って
    // ウィンドウをおおまかに３つに分ける
    //============================================================
    setLayout(new BorderLayout()); // 先頭に this. が省略されている

    // NORTHにテキストフィールドを設定する
    txt1 = new TextField();
    add(txt1, BorderLayout.NORTH);

    //====================================================================
    // パネルを一枚作成し、CENTER にパネルをはめこむ内部をGridLayoutにする
    // パネルを GridLayout にし、パネル内部にボタンを９コ配置する
    //====================================================================
    Panel p_center = new Panel();
    p_center.setLayout(new GridLayout(3, 3));

    for(int i = 1;i<bt.length;i++){
        bt[i] = new Button(""+i);
        p_center.add(bt[i]);
        bt[i].addActionListener(this);
    }
    add(p_center, BorderLayout.CENTER);         // パネルを CENTER に配置

    //=========================================================
    // パネルをもう一枚作成し、SOUTH にパネルをはめこむ
    // そのパネルを GridLayout にし、内部にボタンを２コ配置する
    //=========================================================
    Panel p_south = new Panel();
    p_south.setLayout(new GridLayout(2, 4));

    bt0 = new Button("0");       p_south.add(bt0);
    btClr = new Button("Clear"); p_south.add(btClr);
    btpl = new Button("+");     p_south.add(btpl);
    btmn = new Button("-");     p_south.add(btmn);
    btx = new Button("×");      p_south.add(btx);
    btdv = new Button("÷");     p_south.add(btdv);
    btdt = new Button(".");     p_south.add(btdt);
    bteq = new Button("=");     p_south.add(bteq);
    btmp = new Button("m+");    p_south.add(btmp);
    btmr = new Button("MR");    p_south.add(btmr);
    btrou = new Button("√");    p_south.add(btrou);

    bt0.addActionListener(this);
    btClr.addActionListener(this);
    btpl.addActionListener(this);
    btmn.addActionListener(this);
    btx.addActionListener(this);
    btdv.addActionListener(this);
    btdt.addActionListener(this);
    bteq.addActionListener(this);
    btmp.addActionListener(this);
    btmr.addActionListener(this);
    btrou.addActionListener(this);

    add(p_south, BorderLayout.SOUTH);           // パネルを SOUTH に配置

    setVisible(true);
  }
  public void actionPerformed(ActionEvent e) {    // ボタンクリック処理
    Button btn = (Button)e.getSource();
    if (btn == btClr){ 
        ss = "";
        ss2 = "";
        result = 0;
        t1 = 0;
    }
    else if(btn == btpl || btn == btmn || btn == btx || btn == btdv){
        ss = ss + btn.getLabel();
        ss2 = "";
        if(btn == btpl) k=0;
        else if(btn == btmn){
            if(result==0){
                ss = btn.getLabel();
                ss2 = btn.getLabel();
            }
            else k=1;
        }
        else if(btn == btx) k=2;
        else if(btn == btdv) k=3;
        calculation(k);
    } 
    else if(btn == bteq){
        calculation(k);
        t1 = 0;
        ss = Double.valueOf(result).toString();
    }

    else if(btn == btmp){
        if(result == 0) memory = t1 + memory;
        else memory = memory + result;
    }

    else if(btn == btmr){
        result = memory;
        ss = Double.valueOf(result).toString();
        ss2 = Double.valueOf(result).toString();
    }

    else if(btn == btrou){
        k=4;
        calculation(k);
        ss = Double.valueOf(result).toString();
    }
    else {
        ss = ss + btn.getLabel();
        ss2 = ss2 + btn.getLabel();
        
        t1 = Double.parseDouble(ss2);
       //update(this.getGraphics());                 // (注)
    }
    update(this.getGraphics());
  }

  public void calculation(int k){
        switch (k) {
            case 0:
                result = result + t1 ;
                t1 = 0;
                break;
            case 1:
                if(result == 0) result = t1;
                else result = result - t1;
                t1 = 0;
                break;
            case 2:
                if(result==0) result = t1;
                else result = result * t1;
                t1 = 0;
                break;
            case 3:
                if(result==0) result = t1;
                else result = result / t1;
                t1 = 0;
                break;
            case 4:
                if(result==0) result = Math.sqrt(t1);
                else result = Math.sqrt(result);
                t1 = 0;
                
            default:
                break;
        }
  }

  public void paint(Graphics g) {
    txt1.setText(ss);
  }
}