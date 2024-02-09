package aerg;

import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class routinelogic {

    int posinitial = 0;
    int left = 0;
    int right = 0;
    String initialdate;
    String level;
    String semester;
    String time1;
    String room;

    routinelogic() {

    }

    routinelogic(String i, String l, String s, String t, String r) {
        initialdate = i;
        level = l;
        semester = s;
        time1 = t;
        room = r;
        //System.out.println(initialdate+ " "+level+" "+semester+" "+time1+" "+room);
        // Alldatabase db=new Alldatabase();
      //  System.out.println(i);
    }
    

    void getoptimizesequence() throws SQLException {
        String tablename = "cse" + level + semester;
        //System.out.println(tablename);
        String[] alldatearray = new String[10000];
        String[] alldatearrayor = new String[10000];
        int i = 0;
        String dates = "2024-01-01";
        String datee = "2025-12-30";
        LocalDate start = LocalDate.parse(dates);
        LocalDate end = LocalDate.parse(datee);
        while (!start.isAfter(end)) {
            //System.out.println(start);
            String date1 = start.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            String date11=date1;
           // System.out.println(initialdate.length()+ " "+date1.length());
            if (initialdate.equals(date11)) {
                posinitial = i;
               //System.out.println(date1+" "+i+" "+initialdate);
            }
             
            //  System.out.println(date1);
            alldatearray[i] = date1;
            alldatearrayor[i] = start.toString();
//            if(initialdate==alldatearray[i])
//            {
//                 posinitial = i;
//                 System.out.println(date1+" "+i+" "+initialdate);
//            }
            i++;
            start = start.plusDays(1);
        }
        int longoftime = 10000000;
        
        if (level == "1" && semester == "I") {
            int cnt = 0;
            String[] coursecode = new String[10];
            int[] credit = new int[10];
            Alldatabase db = new Alldatabase();
            List<coursedetails> listofcourse = db.getcoursedetails(tablename);
            String[] holiday = new String[1000];
            String[] specialholiday = new String[1000];
            holiday = db.getholiday("holidays", "dayname");
            specialholiday = db.getholiday("specialdate", "specialdatecol");
           
            for (coursedetails u : listofcourse) {
                String ss = "";
                coursecode[cnt] = u.coursecode;
                ss = u.credit;
                int num = ss.charAt(0) - '0';
                credit[cnt] = num;
                cnt++;
                //System.out.println(u.coursecode+" "+num);
            }
            String ansday[] = new String[10];
            String ansdate[] = new String[10];
            String anscourse[] = new String[10];
            int hakau=0,hakauc=1000;
            for (int j = 0; j < 1; j++) {
                int[] storepos = new int[10];
                
                       if(hakau==hakauc)break;
                                    hakau++;
                for (int a = 0; a < 6; a++) {
                            if(hakau==hakauc)break;
                                    hakau++;
                    int checkhol = 0;
                    int checkspe = 0;
                    //String tempdate[]=new String[10];
                    int tempos = posinitial + j;
                    String current = alldatearray[tempos];
                    String currentorginal = alldatearrayor[tempos];
                    LocalDate lc = LocalDate.parse(currentorginal);
                    DayOfWeek dof = lc.getDayOfWeek();
                    String dayname = dof.toString();
                    int cc = 0;
                    while (holiday[cc] != null) {
                        if (holiday[cc].equals(current)) {
                            checkhol = 1;
                            break;
                        }
                        cc++;

                    }
                    cc = 0;
                    while (specialholiday[cc] != null) {

                        if (specialholiday[cc].equals(current)) {
                            checkspe = 1;
                            break;
                        }
                        cc++;
                    }
                    while (dayname.equalsIgnoreCase("FRIDAY") || dayname.equalsIgnoreCase("SATURDAY")|| checkhol == 1 || checkspe == 1) {
                          // System.out.println(dayname);
                        tempos++;
                        cc = 0;
                        checkhol = 0;
                        checkspe = 0;
                        currentorginal = alldatearrayor[tempos];
                        lc = LocalDate.parse(currentorginal);
                        dof = lc.getDayOfWeek();
                        dayname = dof.toString();
                        current = alldatearray[tempos];
                        while (holiday[cc] != null) {
                            if (holiday[cc].equals(current)) {
                                checkhol = 1;
                                break;
                            }
                            cc++;

                        }
                        cc = 0;
                        while (specialholiday[cc] != null) {

                            if (specialholiday[cc].equals(current)) {
                                checkspe = 1;
                                break;
                            }
                            cc++;
                        }

                    }
                    

                    storepos[0] = tempos;

                    int tempos1 = tempos;
                   
                    for (int b = 0; b < 6; b++) {
                          if(hakau==hakauc)break;
                                    hakau++;
                        if (a == b) {
                            continue;
                        }
                         
                        tempos = tempos1;
                        tempos += credit[b];
                        checkhol = 0;
                        checkspe = 0;

                        current = alldatearray[tempos];
                        currentorginal = alldatearrayor[tempos];
                        lc = LocalDate.parse(currentorginal);
                        dof = lc.getDayOfWeek();
                        dayname = dof.toString();
                        cc = 0;
                        while (holiday[cc] != null) {
                            if (holiday[cc].equals(current)) {
                                checkhol = 1;
                                break;
                            }
                            cc++;

                        }
                        cc = 0;
                        while (specialholiday[cc] != null) {

                            if (specialholiday[cc].equals(current)) {
                                checkspe = 1;
                                break;
                            }
                            cc++;
                        }
                        

                        while (dayname.equalsIgnoreCase("FRIDAY") || dayname.equalsIgnoreCase("SATURDAY")|| checkhol == 1 || checkspe == 1) {
                            tempos++;
                            cc = 0;
                            checkhol = 0;
                            checkspe = 0;
                            currentorginal = alldatearrayor[tempos];
                            lc = LocalDate.parse(currentorginal);
                            dof = lc.getDayOfWeek();
                            dayname = dof.toString();
                            current = alldatearray[tempos];
                            while (holiday[cc] != null) {
                                if (holiday[cc].equals(current)) {
                                    checkhol = 1;
                                    break;
                                }
                                cc++;

                            }
                            cc = 0;
                            while (specialholiday[cc] != null) {

                                if (specialholiday[cc].equals(current)) {
                                    checkspe = 1;
                                    break;
                                }
                                cc++;
                            }

                        }

                        storepos[1] = tempos;
                        tempos1 = tempos;
                              
                        for (int c = 0; c < 6; c++) {
                                 if(hakau==hakauc)break;
                                    hakau++;
                            if (a == c || b == c) {
                                continue;
                            }

                            tempos = tempos1;
                            tempos += credit[c];
                            checkhol = 0;
                            checkspe = 0;

                            current = alldatearray[tempos];
                            currentorginal = alldatearrayor[tempos];
                            lc = LocalDate.parse(currentorginal);
                            dof = lc.getDayOfWeek();
                            dayname = dof.toString();
                            cc = 0;
                            while (holiday[cc] != null) {
                                if (holiday[cc].equals(current)) {
                                    checkhol = 1;
                                    break;
                                }
                                cc++;

                            }
                            cc = 0;
                            while (specialholiday[cc] != null) {

                                if (specialholiday[cc].equals(current)) {
                                    checkspe = 1;
                                    break;
                                }
                                cc++;
                            }

                            while (dayname.equalsIgnoreCase("FRIDAY") || dayname.equalsIgnoreCase("SATURDAY") || checkhol == 1 || checkspe == 1) {
                                tempos++;
                                cc = 0;
                                checkhol = 0;
                                checkspe = 0;
                                currentorginal = alldatearrayor[tempos];
                                lc = LocalDate.parse(currentorginal);
                                dof = lc.getDayOfWeek();
                                dayname = dof.toString();
                                current = alldatearray[tempos];
                                while (holiday[cc] != null) {
                                    if (holiday[cc].equals(current)) {
                                        checkhol = 1;
                                        break;
                                    }
                                    cc++;

                                }
                                cc = 0;
                                while (specialholiday[cc] != null) {

                                    if (specialholiday[cc].equals(current)) {
                                        checkspe = 1;
                                        break;
                                    }
                                    cc++;
                                }

                            }

                            storepos[2] = tempos;
                            tempos1 = tempos;

                            for (int d = 0; d < 6; d++) {
                                   if(hakau==hakauc)break;
                                    hakau++;
                                if (a == d || b == d || c == d) {
                                    continue;
                                }
                              
                                tempos = tempos1;
                                tempos += credit[d];
                                checkhol = 0;
                                checkspe = 0;

                                current = alldatearray[tempos];
                                currentorginal = alldatearrayor[tempos];
                                lc = LocalDate.parse(currentorginal);
                                dof = lc.getDayOfWeek();
                                dayname = dof.toString();
                                cc = 0;
                                while (holiday[cc] != null) {
                                    if (holiday[cc].equals(current)) {
                                        checkhol = 1;
                                        break;
                                    }
                                    cc++;

                                }
                                cc = 0;
                                while (specialholiday[cc] != null) {

                                    if (specialholiday[cc].equals(current)) {
                                        checkspe = 1;
                                        break;
                                    }
                                    cc++;
                                }

                                while (dayname.equalsIgnoreCase("FRIDAY") || dayname.equalsIgnoreCase("SATURDAY") || checkhol == 1 || checkspe == 1) {
                                    tempos++;
                                    cc = 0;
                                    checkhol = 0;
                                    checkspe = 0;
                                    currentorginal = alldatearrayor[tempos];
                                    lc = LocalDate.parse(currentorginal);
                                    dof = lc.getDayOfWeek();
                                    dayname = dof.toString();
                                    current = alldatearray[tempos];
                                    while (holiday[cc] != null) {
                                        if (holiday[cc].equals(current)) {
                                            checkhol = 1;
                                            break;
                                        }
                                        cc++;

                                    }
                                    cc = 0;
                                    while (specialholiday[cc] != null) {

                                        if (specialholiday[cc].equals(current)) {
                                            checkspe = 1;
                                            break;
                                        }
                                        cc++;
                                    }

                                }

                                storepos[3] = tempos;
                                tempos1 = tempos;

                                for (int e = 0; e < 6; e++) {
                                    if(hakau==hakauc)break;
                                    hakau++;
                                    if (a == e || b == e || c == e || d == e) {
                                        continue;
                                    }
                                     // System.out.println("is ok\n");
                                    tempos = tempos1;
                                    tempos += credit[e];
                                    checkhol = 0;
                                    checkspe = 0;

                                    current = alldatearray[tempos];
                                    currentorginal = alldatearrayor[tempos];
                                    lc = LocalDate.parse(currentorginal);
                                    dof = lc.getDayOfWeek();
                                    dayname = dof.toString();
                                    cc = 0;
                                    while (holiday[cc] != null) {
                                        if (holiday[cc].equals(current)) {
                                            checkhol = 1;
                                            break;
                                        }
                                        cc++;

                                    }
                                    cc = 0;
                                    while (specialholiday[cc] != null) {

                                        if (specialholiday[cc].equals(current)) {
                                            checkspe = 1;
                                            break;
                                        }
                                        cc++;
                                    }

                                    while (dayname.equalsIgnoreCase("FRIDAY") || dayname.equalsIgnoreCase("SATURDAY") || checkhol == 1 || checkspe == 1) {
                                        tempos++;
                                        cc = 0;
                                        checkhol = 0;
                                        checkspe = 0;
                                        currentorginal = alldatearrayor[tempos];
                                        lc = LocalDate.parse(currentorginal);
                                        dof = lc.getDayOfWeek();
                                        dayname = dof.toString();
                                        current = alldatearray[tempos];
                                        while (holiday[cc] != null) {
                                            if (holiday[cc].equals(current)) {
                                                checkhol = 1;
                                                break;
                                            }
                                            cc++;

                                        }
                                        cc = 0;
                                        while (specialholiday[cc] != null) {

                                            if (specialholiday[cc].equals(current)) {
                                                checkspe = 1;
                                                break;
                                            }
                                            cc++;
                                        }

                                    }

                                    storepos[4] = tempos;
                                    tempos1 = tempos;

                                    for (int f = 0; f < 6; f++) {
                                        if(hakau==hakauc)break;
                                        hakau++;
                                        if (a == f || b == f || c == f || d == f || e == f) {
                                            continue;
                                        }
                                        
                                        tempos = tempos1;
                                        tempos += credit[f];
                                        checkhol = 0;
                                        checkspe = 0;

                                        current = alldatearray[tempos];
                                        currentorginal = alldatearrayor[tempos];
                                        lc = LocalDate.parse(currentorginal);
                                        dof = lc.getDayOfWeek();
                                        dayname = dof.toString();
                                        cc = 0;
                                        while (holiday[cc] != null) {
                                            if (holiday[cc].equals(current)) {
                                                checkhol = 1;
                                                break;
                                            }
                                            cc++;

                                        }
                                        cc = 0;
                                        while (specialholiday[cc] != null) {

                                            if (specialholiday[cc].equals(current)) {
                                                checkspe = 1;
                                                break;
                                            }
                                            cc++;
                                        }

                                        while (dayname.equalsIgnoreCase("FRIDAY") || dayname.equalsIgnoreCase("SATURDAY")|| checkhol == 1 || checkspe == 1) {
                                            tempos++;
                                            cc = 0;
                                            checkhol = 0;
                                            checkspe = 0;
                                            currentorginal = alldatearrayor[tempos];
                                            lc = LocalDate.parse(currentorginal);
                                            dof = lc.getDayOfWeek();
                                            dayname = dof.toString();
                                            current = alldatearray[tempos];
                                            while (holiday[cc] != null) {
                                                if (holiday[cc].equals(current)) {
                                                    checkhol = 1;
                                                    break;
                                                }
                                                cc++;

                                            }
                                            cc = 0;
                                            while (specialholiday[cc] != null) {

                                                if (specialholiday[cc].equals(current)) {
                                                    checkspe = 1;
                                                    break;
                                                }
                                                cc++;
                                            }

                                        }

                                        storepos[5] = tempos;
                                       if((tempos-j-posinitial)<longoftime)
                                       {
                                           longoftime=tempos-j-posinitial;
                                          anscourse[0]=coursecode[a];
                                          anscourse[1]=coursecode[b];
                                          anscourse[2]=coursecode[c];
                                          anscourse[3]=coursecode[d];
                                          anscourse[4]=coursecode[e];
                                          anscourse[5]=coursecode[f];
                                          for(int xx=0;xx<6;xx++)
                                          {
                                              int r=storepos[xx];
                                              ansdate[xx]=alldatearray[r];
                                         currentorginal = alldatearrayor[r];
                                        lc = LocalDate.parse(currentorginal);
                                        dof = lc.getDayOfWeek();
                                        dayname = dof.toString();
                                              ansday[xx]=dayname;
                                          }
                                           
                                       }

                                    }
                                }
                            }
                        }
                    }
                }

            }
           // System.out.println(holiday[0]+" "+alldatearray[0]);
            for(i=0;i<6;i++)
            {
               // System.out.println(ansdate[i]+ " "+ansday[i]+" "+anscourse[i]);
                Alldatabase db1=new Alldatabase();
                db1.insertresult(ansdate[i], ansday[i], anscourse[i]);
            }

        }

    }

}
