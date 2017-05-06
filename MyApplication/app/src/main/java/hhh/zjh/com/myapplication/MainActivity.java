package hhh.zjh.com.myapplication;

import android.os.Bundle;//方法用到的;Bundle是将数据传递到另一个上下文中或保存或回复你自己状态的数据存储方式。它的数据不是持久化状态。
import android.support.v7.app.AppCompatActivity;
import android.util.Log;//打印LOG信息的
import android.view.View;//引用VIEW用到的，就是视图
import android.widget.Button;//按键
import android.widget.EditText;//EditText 相当于很多程序中的TextField，是一个可以让用户输入文本的组件，
                               // 是用户和程序之间用于传输数据的纽带，通过它用户可以把数据传送给Android程序

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
//implements View.OnClickListener声明实现OnClickListener接口成为监听对象
//onClick()就是OnClickListener接口中规定必须编写的方法，让MainActivity具备可以处理“单击”事件的能力


    // 数字
    private Button num0;
    private Button num1;
    private Button num2;
    private Button num3;
    private Button num4;
    private Button num5;
    private Button num6;
    private Button num7;
    private Button num8;
    private Button num9;

     //运算符
    private Button plus_btn;//+
    private Button subtract_btn;//-
    private Button multiply_btn;//×
    private Button divide_btn;//÷
    private Button equal_btn;//=

     //其他
    private Button dot_btn;//.
    private Button percent_btn;//%
    private Button delete_btn;//DEL
    private Button ac_btn;//AC

     // 结果
    private EditText mResultText;//输入输出栏

     //已经输入的字符
    private String existedText = "";//定义一个private的string变量用于接受值的输入（用于计算的值和之间操作（+-*/））和输出（计算结果）

     //是否计算过
    private boolean isCounted = false;//当监听到“=”按下时，isCounted为true

     //以负号开头，且运算符不是减号
     //例如：-21×2
    private boolean startWithOperator = false;

     //以负号开头，且运算符是减号
      //例如：-21-2
    private boolean startWithSubtract = false;

     //不以负号开头，且包含运算符
     //例如：21-2
    private boolean noStartWithOperator = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) { //Android 启动任何一个Activity时，它会完成
                                                         // 一些必要的初始化工作，然后就去调用改Activity的OnCreate（）方法，OnCreate（）是MainActivity第一件要做的事
        super.onCreate(savedInstanceState);//super就是要指定调用父类的onCreate()，然后调用
                                           // setContentView()把画面（view）的内容（content）显示出来。其中savedInstanceState是把
                                           // 当前系统所记载该Activity之前的状态传进来。这是应为onCreate()不一定是程序第一次执行才被调用，
                                           // 当程序被系统强制停止之后又恢复时也会被调用，此时就要继续中断前被系统所保存（save）的执行状态（Instance State）
                                           //这样可以使那些输入到一半就被中断的数据不会遗失
        setContentView(R.layout.activity_main);//载入布局文件

        initView();  //初始视图（界面控件）
        initEvent();//初始化新事件对象的属性
    }


      //初始化控件
    private void initView() {

        // 数字

        //findViewById():根据指定的资源ID找出对应的View对象，由于findViewById()函数返回的是
        // View类的对象，因此需要强制转换成组件真正所属的类型，才能使用到组件特有的功能。
        //所以一下的变量除了result_text是转成EditText，其余的都是转成Button
        num0 = (Button) findViewById(R.id.num_zero);
        num1 = (Button) findViewById(R.id.num_one);
        num2 = (Button) findViewById(R.id.num_two);
        num3 = (Button) findViewById(R.id.num_three);
        num4 = (Button) findViewById(R.id.num_four);
        num5 = (Button) findViewById(R.id.num_five);
        num6 = (Button) findViewById(R.id.num_six);
        num7 = (Button) findViewById(R.id.num_seven);
        num8 = (Button) findViewById(R.id.num_eight);
        num9 = (Button) findViewById(R.id.num_nine);

         // 运算符
        plus_btn = (Button) findViewById(R.id.plus_btn);
        subtract_btn = (Button) findViewById(R.id.subtract_btn);
        multiply_btn = (Button) findViewById(R.id.multiply_btn);
        divide_btn = (Button) findViewById(R.id.divide_btn);
        equal_btn = (Button) findViewById(R.id.equal_btn);

        // 其他
        dot_btn = (Button) findViewById(R.id.dot_btn);
        percent_btn = (Button) findViewById(R.id.percent_btn);
        delete_btn = (Button) findViewById(R.id.delete_btn);
        ac_btn = (Button) findViewById(R.id.ac_btn);

         // 结果
        mResultText = (EditText) findViewById(R.id.result_text);

        // 已经输入的字符
        existedText = mResultText.getText().toString();//.getText()获取用户输入的文字,由于.getText()方法
        // 返回的是Android SDK中定义的Editable类型的对象，因此要当字符串处理，必须再调用toString()方法进行转换

    }


     //初始化事件
    //setOnClickListener,登录（set）监听对象，this表示MainActivity对象本身
    //先找(findViewById)再监听（setOnClickListener）
    private void initEvent() {
        num0.setOnClickListener(this);
        num1.setOnClickListener(this);
        num2.setOnClickListener(this);
        num3.setOnClickListener(this);
        num4.setOnClickListener(this);
        num5.setOnClickListener(this);
        num6.setOnClickListener(this);
        num7.setOnClickListener(this);
        num8.setOnClickListener(this);
        num9.setOnClickListener(this);

        plus_btn.setOnClickListener(this);
        subtract_btn.setOnClickListener(this);
        multiply_btn.setOnClickListener(this);
        divide_btn.setOnClickListener(this);
        equal_btn.setOnClickListener(this);

        dot_btn.setOnClickListener(this);
        percent_btn.setOnClickListener(this);
        delete_btn.setOnClickListener(this);
        ac_btn.setOnClickListener(this);
    }


     //点击事件
     // @param v 点击的控件
    @Override
    public void onClick(View v) {

        switch (v.getId()) {

             //数字
            case R.id.num_zero:
                existedText = isOverRange(existedText, "0");//传值到existedText,但要先通过isOverRange（）对被传值做系列判断；isOverRange（）
                break;
            case R.id.num_one:
                existedText = isOverRange(existedText, "1");
                break;
            case R.id.num_two:
                existedText = isOverRange(existedText, "2");
                break;
            case R.id.num_three:
                existedText = isOverRange(existedText, "3");
                break;
            case R.id.num_four:
                existedText = isOverRange(existedText, "4");
                break;
            case R.id.num_five:
                existedText = isOverRange(existedText, "5");
                break;
            case R.id.num_six:
                existedText = isOverRange(existedText, "6");
                break;
            case R.id.num_seven:
                existedText = isOverRange(existedText, "7");
                break;
            case R.id.num_eight:
                existedText = isOverRange(existedText, "8");
                break;
            case R.id.num_nine:
                existedText = isOverRange(existedText, "9");
                break;

             //运算符
            case R.id.plus_btn:

                 //判断已有的字符是否是科学计数
                 //是 置零
                 //否 进行下一步

                 //判断表达式是否可以进行计算
                 //是 先计算再添加字符
                 //否 添加字符

                 //判断计算后的字符是否是 error
                 //是 置零
                 //否 添加运算符

                if (!existedText.contains("e")) {

                    if (judgeExpression()) {
                        existedText = getResult();
                        if (existedText.equals("error")) {

                        } else {
                            existedText += "+";
                        }
                    } else {

                        if (isCounted) {
                            isCounted = false;
                        }

                        if ((existedText.substring(existedText.length() - 1)).equals("-")) {
                            existedText = existedText.replace("-", "+");
                        } else if ((existedText.substring(existedText.length() - 1)).equals("×")) {
                            existedText = existedText.replace("×", "+");
                        } else if ((existedText.substring(existedText.length() - 1)).equals("÷")) {
                            existedText = existedText.replace("÷", "+");
                        } else if (!(existedText.substring(existedText.length() - 1)).equals("+")) {
                            existedText += "+";
                        }
                    }
                } else {
                    existedText = "0";
                }

                break;
            case R.id.subtract_btn:

                if (!existedText.contains("e")) {
                    if (judgeExpression()) {
                        existedText = getResult();
                        if (existedText.equals("error")) {

                        } else {
                            existedText += "-";
                        }
                    } else {

                        if (isCounted) {
                            isCounted = false;
                        }

                        if ((existedText.substring(existedText.length() - 1)).equals("+")) {
//                    Log.d("Anonymous", "onClick: " + "进入减法方法");
                            existedText = existedText.replace("+", "-");
                        } else if ((existedText.substring(existedText.length() - 1)).equals("×")) {
                            existedText = existedText.replace("×", "-");
                        } else if ((existedText.substring(existedText.length() - 1)).equals("÷")) {
                            existedText = existedText.replace("÷", "-");
                        } else if (!(existedText.substring(existedText.length() - 1)).equals("-")) {
                            existedText += "-";
                        }
                    }
                } else {
                    existedText = "0";
                }
                break;
            case R.id.multiply_btn:

                if (!existedText.contains("e")) {
                    if (judgeExpression()) {
                        existedText = getResult();
                        if (existedText.equals("error")) {

                        } else {
                            existedText += "×";
                        }

                    } else {

                        if (isCounted) {
                            isCounted = false;
                        }

                        if ((existedText.substring(existedText.length() - 1)).equals("+")) {
                            existedText = existedText.replace("+", "×");//替换号
                        } else if ((existedText.substring(existedText.length() - 1)).equals("-")) {
                            existedText = existedText.replace("-", "×");
                        } else if ((existedText.substring(existedText.length() - 1)).equals("÷")) {
                            existedText = existedText.replace("÷", "×");
                        } else if (!(existedText.substring(existedText.length() - 1)).equals("×")) {
                            existedText += "×";
                        }
                    }
                } else {
                    existedText = "0";
                }
                break;
            case R.id.divide_btn:

                if (!existedText.contains("e")) {
                    if (judgeExpression()) {
                        existedText = getResult();
                        if (existedText.equals("error")) {

                        } else {
                            existedText += "÷";
                        }

                    } else {

                        if (isCounted) {
                            isCounted = false;
                        }

                        if ((existedText.substring(existedText.length() - 1)).equals("+")) {
                            existedText = existedText.replace("+", "÷");
                        } else if ((existedText.substring(existedText.length() - 1)).equals("-")) {
                            existedText = existedText.replace("-", "÷");
                        } else if ((existedText.substring(existedText.length() - 1)).equals("×")) {
                            existedText = existedText.replace("×", "÷");
                        } else if (!(existedText.substring(existedText.length() - 1)).equals("÷")) {
                            existedText += "÷";
                        }
                    }
                } else {
                    existedText = "0";
                }
                break;
            case R.id.equal_btn:
                existedText = getResult();
                isCounted = true;
                break;
            /**
             * 其他
             */
            case R.id.dot_btn:

                 //判断是否运算过
                 //否
                 //判断是否有运算符，有 判断运算符之后的数字，无 判断整个数字
                 //判断数字是否过长，是则不能添加小数点，否则可以添加
                 //判断已经存在的数字里是否有小数点
                 //是
                 //字符串置为 0.

                if (!isCounted) {

                    if (existedText.contains("+") || existedText.contains("-") ||
                            existedText.contains("×") || existedText.contains("÷")) {

                        String param1 = null;
                        String param2 = null;

                        if (existedText.contains("+")) {
                            param1 = existedText.substring(0, existedText.indexOf("+"));
                            param2 = existedText.substring(existedText.indexOf("+") + 1);
                        } else if (existedText.contains("-")) {
                            param1 = existedText.substring(0, existedText.indexOf("-"));
                            param2 = existedText.substring(existedText.indexOf("-") + 1);
                        } else if (existedText.contains("×")) {
                            param1 = existedText.substring(0, existedText.indexOf("×"));
                            param2 = existedText.substring(existedText.indexOf("×") + 1);
                        } else if (existedText.contains("÷")) {
                            param1 = existedText.substring(0, existedText.indexOf("÷"));
                            param2 = existedText.substring(existedText.indexOf("÷") + 1);
                        }
                        Log.d("Anonymous param1", param1);
                        Log.d("Anonymous param2", param2);

                        boolean isContainedDot = param2.contains(".");
                        if (param2.length() >= 9) {

                        } else if (!isContainedDot) {
                            if (param2.equals("")) {
                                existedText += "0.";
                            } else {
                                existedText += ".";
                            }
                        } else {
                            return;
                        }
                    } else {
                        boolean isContainedDot = existedText.contains(".");
                        if (existedText.length() >= 9) {

                        } else if (!isContainedDot) {
                            existedText += ".";
                        } else {
                            return;
                        }
                    }
                    isCounted = false;

                } else {
                    existedText = "0.";
                    isCounted = false;
                }


                break;
            case R.id.percent_btn:

                 //判断数字是否有运算符
                 //是 不做任何操作
                 //否 进行下一步

                 //判断数字是否是 0
                 //是 不做任何操作
                 //否 进行除百

                 //将字符串转换成double类型，进行运算后，再转换成String型

                if (existedText.equals("error")) {

                } else {

                    getCondition();

                    if (startWithOperator || startWithSubtract || noStartWithOperator) {

                    } else {
                        if (existedText.equals("0")) {
                            return;
                        } else {
                            double temp = Double.parseDouble(existedText);
                            temp = temp / 100;
                            existedText = String.valueOf(temp);
                        }
                    }
                }
                break;
            case R.id.delete_btn:

                 //字符串长度大于 0 时才截取字符串
                 //如果长度为 1，则直接把字符串设置为 0

                if (existedText.equals("error")) {
                    existedText = "0";
                } else if (existedText.length() > 0) {
                    if (existedText.length() == 1) {
                        existedText = "0";
                    } else {
                        existedText = existedText.substring(0, existedText.length() - 1);
                    }
                }
                break;
            case R.id.ac_btn:
                existedText = "0";
                break;
        }

         //置显示

        mResultText.setText(existedText);
    }


     //先判断是否按过等于号
     //是 按数字则显示当前数字
     //否 在已有的表达式后添加字符

     //判断数字是否就是一个 0
     //是 把字符串设置为空字符串。
     //1、打开界面没有运算过的时候，AC键归零或删除完归零的时候，会显示一个 0
     //2、当数字是 0 的时候，设置成空字符串，再按 0 ，数字会还是 0，不然可以按出 000 这种数字
     //否 添加按下的键的字符

     //判断数字是否包含小数点
     //是 数字不能超过十位
     //否 数字不能超过九位

     //进行上面的判断后，再判断数字是否超过长度限制
     //超过不做任何操作
     //没超过可以再添数字

    private String isOverRange(String existedText, String s) {

         //判断是否计算过
        if (!isCounted) {

              //判断是否是科学计数
              //是 文本置零
            if (existedText.contains("e")) {
                existedText = "0";
            }

             //判断是否只有一个 0
             //是 文本清空
            if (existedText.equals("0")) {
                existedText = "";
            }

              //判断是否有运算符
              //是 判断第二个数字
              //否 判断整个字符串

            if (existedText.contains("+") || existedText.contains("-") ||
                    existedText.contains("×") || existedText.contains("÷")) {

                //包括运算符时 两个数字 判断第二个数字
                //两个参数
                String param2 = null;
                if (existedText.contains("+")) {
                    param2 = existedText.substring(existedText.indexOf("+") + 1);
                } else if (existedText.contains("-")) {
                    param2 = existedText.substring(existedText.indexOf("-") + 1);
                } else if (existedText.contains("×")) {
                    param2 = existedText.substring(existedText.indexOf("×") + 1);
                } else if (existedText.contains("÷")) {
                    param2 = existedText.substring(existedText.indexOf("÷") + 1);
                }

//            Log.d("Anonymous param1",param1);
//            Log.d("Anonymous param2",param2);
                if (existedText.substring(existedText.length() - 1).equals("+") ||
                        existedText.substring(existedText.length() - 1).equals("-") ||
                        existedText.substring(existedText.length() - 1).equals("×") ||
                        existedText.substring(existedText.length() - 1).equals("÷")) {
                    existedText += s;
                } else {
                    if (param2.contains(".")) {
                        if (param2.length() >= 10) {

                        } else {
                            existedText += s;
                        }
                    } else {
                        if (param2.length() >= 9) {

                        } else {
                            existedText += s;
                        }
                    }
                }
            } else {

                 //不包括运算符时 一个数字
                if (existedText.contains(".")) {
                    if (existedText.length() >= 10) {

                    } else {
                        existedText += s;
                    }
                } else {
                    if (existedText.length() >= 9) {

                    } else {
                        existedText += s;
                    }
                }
            }

            isCounted = false;

        } else {

            existedText = s;
            isCounted = false;

        }


        return existedText;
    }


     //判断表达式
     //为了按等号是否进行运算
     //以及出现两个运算符（第一个参数如果为负数的符号不计）先进行运算再添加运算符
    private boolean judgeExpression() {
        getCondition();

        String tempParam2 = null;

        if ( startWithOperator || noStartWithOperator || startWithSubtract) {

            if (existedText.contains("+")) {

                 //先获取第二个参数
                tempParam2 = existedText.substring(existedText.indexOf("+") + 1);

                  //如果第二个参数为空，表达式不成立
                if (tempParam2.equals("")) {
                    return false;
                } else {
                    return true;
                }
            } else if (existedText.contains("×")) {

                tempParam2 = existedText.substring(existedText.indexOf("×") + 1);

                if (tempParam2.equals("")) {
                    return false;
                } else {
                    return true;
                }

            } else if (existedText.contains("÷")) {

                tempParam2 = existedText.substring(existedText.indexOf("÷") + 1);

                if (tempParam2.equals("")) {
                    return false;
                } else {
                    return true;
                }

            } else if (existedText.contains("-")) {

                 //这里是以最后一个 - 号为分隔去取出两个参数
                 //进到这个方法，必须满足有运算公式
                 //而又避免了第一个参数是负数的情况
                tempParam2 = existedText.substring(existedText.lastIndexOf("-") + 1);

                if (tempParam2.equals("")) {
                    return false;
                } else {
                    return true;
                }

            }
        }
        return false;
    }


    //取得判断条件
    private void getCondition() {

         //以负号开头，且运算符不是是减号
         //例如：-21×2
        startWithOperator = existedText.startsWith("-") && ( existedText.contains("+") ||
                existedText.contains("×") || existedText.contains("÷") );

         //以负号开头，且运算符是减号
         //例如：-21-2
        startWithSubtract = existedText.startsWith("-") && ( existedText.lastIndexOf("-") != 0 );

         //不以负号开头，且包含运算符
         // 例如：21-2
        noStartWithOperator = !existedText.startsWith("-") && ( existedText.contains("+") ||
                existedText.contains("-") || existedText.contains("×") || existedText.contains("÷"));
    }



     //进行运算，得到结果
     //@return  返回结果
    private String getResult() {


        //结果
        String tempResult = null;

        //两个String类型的参数
        String param1 = null;
        String param2 = null;

         // 转换后的两个double类型的参数
        double arg1 = 0;
        double arg2 = 0;
        double result = 0;

        getCondition();


         //如果有运算符，则进行运算
         //没有运算符，则把已经存在的数据再传出去
        if ( startWithOperator || noStartWithOperator || startWithSubtract) {

            if (existedText.contains("+")) {

                 //先获取两个参数
                param1 = existedText.substring(0, existedText.indexOf("+"));
                param2 = existedText.substring(existedText.indexOf("+") + 1);

                 //如果第二个参数为空，则还是显示当前字符
                if (param2.equals("")){
                    tempResult = existedText;
                } else {

                     //转换String为Double
                     //计算后再转换成String类型
                     // 进行正则表达式处理
                    arg1 = Double.parseDouble(param1);
                    arg2 = Double.parseDouble(param2);
                    result = arg1 + arg2;
                    tempResult = String.format("%f", result);
                    tempResult = subZeroAndDot(tempResult);
                }


            } else if (existedText.contains("×")) {

                param1 = existedText.substring(0, existedText.indexOf("×"));
                param2 = existedText.substring(existedText.indexOf("×") + 1);

                if (param2.equals("")){
                    tempResult = existedText;
                } else {
                    arg1 = Double.parseDouble(param1);
                    arg2 = Double.parseDouble(param2);
                    result = arg1 * arg2;
                    tempResult = String.format("%f", result);
                    tempResult = subZeroAndDot(tempResult);
                }

            } else if (existedText.contains("÷")) {

                param1 = existedText.substring(0, existedText.indexOf("÷"));
                param2 = existedText.substring(existedText.indexOf("÷") + 1);

                if (param2.equals("0")){
                    tempResult = "error";
                } else if (param2.equals("")){
                    tempResult = existedText;
                } else {
                    arg1 = Double.parseDouble(param1);
                    arg2 = Double.parseDouble(param2);
                    result = arg1 / arg2;
                    tempResult = String.format("%f", result);
                    tempResult = subZeroAndDot(tempResult);
                }

            } else if (existedText.contains("-")) {


                 //这里是以最后一个 - 号为分隔去取出两个参数
                 //进到这个方法，必须满足有运算公式
                 //而又避免了第一个参数是负数的情况
                param1 = existedText.substring(0, existedText.lastIndexOf("-"));
                param2 = existedText.substring(existedText.lastIndexOf("-") + 1);

                if (param2.equals("")){
                    tempResult = existedText;
                } else {
                    arg1 = Double.parseDouble(param1);
                    arg2 = Double.parseDouble(param2);
                    result = arg1 - arg2;
                    tempResult = String.format("%f", result);
                    tempResult = subZeroAndDot(tempResult);
                }

            }

             //如果数据长度大于等于10位，进行科学计数

             //如果有小数点，再判断小数点前是否有十个数字，有则进行科学计数

            if (tempResult.length() >= 10) {
                tempResult = String.format("%e", Double.parseDouble(tempResult));
            } else if (tempResult.contains(".")) {
                if (tempResult.substring(0, tempResult.indexOf(".")).length() >= 10) {
                    tempResult = String.format("%e", Double.parseDouble(tempResult));
                }
            }
        } else {
            tempResult = existedText;
        }

        return tempResult;
    }

      //使用java正则表达式去掉多余的.与0
      //@param s 传入的字符串
      //@return 修改之后的字符串

    public static String subZeroAndDot(String s){
        if(s.indexOf(".") > 0){
            s = s.replaceAll("0+?$", "");//去掉多余的0
            s = s.replaceAll("[.]$", "");//如最后一位是.则去掉
        }
        return s;
    }


}



