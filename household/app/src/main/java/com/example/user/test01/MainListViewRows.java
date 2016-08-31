package com.example.user.test01;

/**
 * Created by USER on 2016-08-08.
 */
public class MainListViewRows {

        private String account;
        private String amount;
        private String date;

        public MainListViewRows(String _account, String _amount, String _date) {
            this.account = _account;
            this.amount = _amount;
            this.date = _date;
        }

        public String getAccount() {
            return account;
        }

        public void setAccount(String account) {
            this.account = account;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }
}



