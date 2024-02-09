/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aerg;

import static java.lang.System.out;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 *
 * @author User
 */
public class logicbydp {

    String initialdate, level, semester;
    String alldate[] = new String[110];
    String orginaldate[] = new String[110];
    int subject[] = new int[20];
    String ansdate[]=new String[20];
    String ansday[]=new String[20];
    String anscourse[]=new String[20];
    int ansval=0;
    String[] coursecode = new String[20];

    String[] holiday = new String[1000];
    String[] specialholiday = new String[1000];

    int[] credit = new int[20];
    int fredate[] = new int[110];
    String datestring = "";
    int posoffreeday = 0;
    int main_ans;
    int totalsub = 0;
    int ck;
    int dp[][] = new int[110][(1 << 15)];

    int ok(int i, int j) {
        if (j == (1 << totalsub) - 1) {
            return 0;
        } else if (i == datestring.length()) {
            return 200;
        } else if (dp[i][j] != -1) {
            return dp[i][j];
        } else {
            int ans = 200;
            // ans=min(ans,(j>0)+ok(i+1,j));
            int rr = ok(i + 1, j);
            if (j > 0) {
                rr += 1;
            }
            if (rr <= ans) {
                ans = rr;
            }
            for (int l = 0; l < totalsub; l++) {
                if (((1 << l) & j) != 0) {
                    continue;
                }
                int a = 0;
                if (j > 0) {
                    a = credit[l];
                }
//               auto it = lower_bound(fre.begin(), fre.end(), i + a);
//                if (it == fre.end()) {
//                    continue;
//                }
                int peyeci = -1;
                for (int x = 0; x < posoffreeday; x++) {
                    if (fredate[x] >= i + a) {
                        peyeci = fredate[x];
                        break;
                    }
                }
                if (peyeci == -1) {
                    continue;
                }
                int r = peyeci;
                ans = Math.min(ans, r - i + ok(r + 1, j | (1 << l)));
            }
            return dp[i][j] = ans;

        }

    }

    void cal(int i, int j, int sum) {
        if (j == (1 << totalsub) - 1) {
            return;
        } else {
               int rrr=0;
               if(j>0)rrr=1;
            if (ok(i + 1, j) + rrr + sum == ck) {
                cal(i + 1, j, sum + rrr);
                return;
            }
            for (int l = 0; l < totalsub; l++) {
                if (((1 << l) & j)!=0) {
                    continue;
                }
                int a = 0;
                if (j > 0) {
                    a = credit[l];
                }
//                auto it = lower_bound(fre.begin(), fre.end(), i + a);
//                if (it == fre.end()) {
//                    continue;
//                }
//                int r = ( * it);

                                int peyeci = -1;
                for (int x = 0; x < posoffreeday; x++) {
                    if (fredate[x] >= i + a) {
                        peyeci = fredate[x];
                        break;
                    }
                }
                if (peyeci == -1) {
                    continue;
                }
                int r = peyeci;
                
                if (ok(r + 1, j | (1 << l)) + r - i + sum == ck) {
                   // cout << r + 1 << " th number din a exam hbe " << l + 1 << endl;
                      ansdate[ansval]=orginaldate[r];
                     // alldate[r];
                LocalDate lc = LocalDate.parse(alldate[r]);
                DayOfWeek dof = lc.getDayOfWeek();
                String dayname = dof.toString();
                      ansday[ansval]=dayname;
                      anscourse[ansval]=coursecode[l];
                   // System.out.println((r+1)+" th din a exam hobe"+(l+1));
                     ansval++;
                    cal(r + 1, j | (1 << l), sum + r - i);
                    break;
                }
            }
        }

    }

    void solve() {
        // System.out.println(totalsub);
        for (int i = 0; i < datestring.length(); i++) {
            for (int j = 0; j < (1 << totalsub); j++) {
                dp[i][j] = -1;
            }
        }
        main_ans = ok(0, 0);
        //  System.out.println(main_ans);
        ck = main_ans;
        cal(0, 0, 0);
    }

    logicbydp(String indate, String lev, String sem, int checkfriday, int checksaturday) throws SQLException {
        initialdate = indate;
        level = lev;
        semester = sem;
        int checker = 0;
        int i = 0;
        // System.out.println(indate+" "+lev+" "+sem);
        String dates = "2024-01-01";
        String datee = "2025-12-30";
        LocalDate start = LocalDate.parse(dates);
        LocalDate end = LocalDate.parse(datee);
        while (!start.isAfter(end)) {
            //System.out.println(start);
            String date1 = start.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            String date11 = date1;
            // System.out.println(initialdate.length()+ " "+date1.length());
            if (i >= 25) {
                break;
            }
            if (initialdate.equals(date11) || checker == 1) {
                orginaldate[i] = date1;
                alldate[i] = start.toString();
                checker = 1;
                i++;
            }

            start = start.plusDays(1);
        }
        int cnt = 0;
        Alldatabase db = new Alldatabase();
        List<coursedetails> listofcourse = db.getcoursedetails("cse" + lev + sem);
        for (coursedetails u : listofcourse) {
            String ss = "";
            totalsub++;
            coursecode[cnt] = u.coursecode;
            ss = u.credit;
            int num = ss.charAt(0) - '0';
            credit[cnt] = num;
            cnt++;
            //System.out.println(u.coursecode+" "+num);
        }

        holiday = db.getholiday("holidays", "dayname");
        specialholiday = db.getholiday("specialdate", "specialdatecol");
        for (i = 0; orginaldate[i] != null; i++) {
            int check = 1;
            for (int j = 0; holiday[j] != null; j++) {
                if (orginaldate[i].equals(holiday[j])) {
                    check = 0;
                    break;
                }
            }
            for (int j = 0; specialholiday[j] != null; j++) {
                if (orginaldate[i].equals(specialholiday[j])) {
                    check = 0;
                    break;
                }
            }

            LocalDate lc = LocalDate.parse(alldate[i]);
            DayOfWeek dof = lc.getDayOfWeek();
            String dayname = dof.toString();
            // System.out.println(dayname);
            if (dayname.equalsIgnoreCase("FRIDAY")) {
                if (checkfriday == 0) {
                    check = 0;
                }
            }
            if (dayname.equalsIgnoreCase("SATURDAY")) {
                if (checksaturday == 0) {
                    check = 0;
                }
            }
            if (check == 1) {
                datestring += "1";
                fredate[posoffreeday] = i;
                posoffreeday++;
            } else {
                datestring += "0";
            }
        }
        solve();
//        for(i=0;i<totalsub;i++)
//        {
//            System.out.println(ansdate[i]+" "+ansday[i]+" "+anscourse[i]);
//        }
            
           for(i=0;i<totalsub;i++)
            {
               // System.out.println(ansdate[i]+ " "+ansday[i]+" "+anscourse[i]);
                Alldatabase db1=new Alldatabase();
                db1.insertresult(ansdate[i], ansday[i], anscourse[i]);
            }

    }

}
