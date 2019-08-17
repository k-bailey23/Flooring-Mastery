/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.ui;

import java.math.BigDecimal;
import java.util.Scanner;

/**
 *
 * @author keb03_000
 */
public class UserIOConsoleImpl implements UserIO {

    Scanner scanner = new Scanner(System.in);

    @Override
    public void print(String msg) {
        System.out.println(msg);
    }

    @Override
    public int readInt(String prompt) {
        int i = 0;
        boolean isValid = false;
        this.print(prompt);
        while (!isValid) {
            String s = scanner.nextLine();
            try {
                i = Integer.parseInt(s);
                break;
            } catch (NumberFormatException e) {
                this.print("Error. Enter int");
                this.print(prompt);
            }
        }
        return i;
    }

    @Override
    public int readInt(String prompt, int min, int max) {
        int i = 0;
        boolean isValid = false;
        while (!isValid) {
            i = readInt(prompt);
            if (i >= min && i <= max) {
                isValid = true;
            } else {
                this.print(prompt);
            }
        }
        return i;
    }

    @Override
    public double readDouble(String prompt) {
        double d = 0;
        boolean isValid = false;
        this.print(prompt);
        while (!isValid) {
            String s = scanner.nextLine();
            try {
                d = Double.parseDouble(s);
                break;
            } catch (NumberFormatException e) {
                this.print("Error. Enter double");
                this.print(prompt);
            }
        }
        return d;
    }

    @Override
    public double readDouble(String prompt, double min, double max) {
        double d = 0;
        boolean isValid = false;
        while (!isValid) {
            d = readDouble(prompt);
            if (d >= min && d <= max) {
                isValid = true;
            } else {
                this.print(prompt);
            }
        }
        return d;
    }

    @Override
    public float readFloat(String prompt) {
        float f = 0;
        boolean isValid = false;
        this.print(prompt);
        while (!isValid) {
            String s = scanner.nextLine();
            try {
                f = Float.parseFloat(s);
                break;
            } catch (NumberFormatException e) {
                this.print("Error. Enter float");
                this.print(prompt);
            }
        }
        return f;
    }

    @Override
    public float readFloat(String prompt, float min, float max) {
        float f = 0;
        boolean isValid = false;
        while (!isValid) {
            f = readFloat(prompt);
            if (f >= min && f <= max) {
                isValid = true;
            } else {
                this.print(prompt);
            }
        }
        return f;
    }

    @Override
    public long readLong(String prompt) {
        long l = 0;
        boolean isValid = false;
        this.print(prompt);
        while (!isValid) {
            String s = scanner.nextLine();
            try {
                l = Long.parseLong(s);
                break;
            } catch (NumberFormatException e) {
                this.print("Error. Enter long");
                this.print(prompt);
            }
        }
        return l;
    }

    @Override
    public long readLong(String prompt, long min, long max) {
        long l = 0;
        boolean isValid = false;
        while (!isValid) {
            l = readLong(prompt);
            if (l >= min && l <= max) {
                isValid = true;
            } else {
                this.print(prompt);
            }
        }
        return l;
    }

    @Override
    public BigDecimal readBigDecimal(String prompt) {
        BigDecimal bd = null;
        boolean isValid = false;
        this.print(prompt);
        while (!isValid) {
            try {
                String s = scanner.nextLine();
                bd = new BigDecimal(s);
                isValid = true;
            } catch (NumberFormatException e) {
                this.print("Error. Enter BigDecimal");
            }
        }
        return bd;
    }

    @Override
    public BigDecimal readBigDecimal(String prompt, BigDecimal min, BigDecimal max) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String readString(String prompt) {
        this.print(prompt);
        return scanner.nextLine();
    }

}
