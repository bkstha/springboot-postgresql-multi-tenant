package com.journal.app.controllers;

import com.journal.app.database.MultiTenantRegistration;
import com.journal.app.models.domain.AccountGroup;
import com.journal.app.models.domain.UniqueId;
import com.journal.app.models.enums.ACCOUNT;
import com.journal.app.models.services.AccountGroupService;
import com.journal.app.models.services.UniqueIdService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

@Component
public class App {

    private static final Logger logger = LogManager.getLogger(App.class);

    public static String trim(String value) {
        return value;
    }

    public static void setDefaultData(String schema, Session schemaSession, Transaction schemaTransaction) {
        try {
            System.out.println("uniqueIdCount: " + UniqueIdService.isUniqueIdEmpty(schemaSession));
            if (UniqueIdService.isUniqueIdEmpty(schemaSession)) {
                UniqueId u1 = new UniqueId("P", 1);
                UniqueId u2 = new UniqueId("SI", 1);
                UniqueId u3 = new UniqueId("PI", 1);
                schemaSession.save(u1);
                schemaSession.save(u2);
                schemaSession.save(u3);
                logger.info("Default Unique Id Generated.");

            }

            if (AccountGroupService.isTableEmpty(schemaSession)) {
                AccountGroup ag = new AccountGroup(ACCOUNT.A, "current assets", 0L, false, false);
                schemaSession.save(ag);
                AccountGroup agl = new AccountGroup(ACCOUNT.A, "long-term assets", 0L, false, false);
                schemaSession.save(agl);
                AccountGroup agc = new AccountGroup(ACCOUNT.A, "debtors", ag.getId(), false, true);
                schemaSession.save(agc);
                AccountGroup agc1 = new AccountGroup(ACCOUNT.A, "loans & advance", ag.getId(), false, true);
                schemaSession.save(agc1);
                AccountGroup ag1 = new AccountGroup(ACCOUNT.A, "fixed assets", 0L, false, false);
                schemaSession.save(ag1);

                AccountGroup ag111 = new AccountGroup(ACCOUNT.A, "lands & Building", ag1.getId(), false, true);
                schemaSession.save(ag111);
                AccountGroup ag112 = new AccountGroup(ACCOUNT.A, "equipment", ag1.getId(), false, true);
                schemaSession.save(ag112);
                AccountGroup ag113 = new AccountGroup(ACCOUNT.A, "vehicles", ag1.getId(), false, true);
                schemaSession.save(ag113);
                AccountGroup ag114 = new AccountGroup(ACCOUNT.A, "accumulated depreciation - equipment", ag1.getId(), false, true);
                schemaSession.save(ag114);
                AccountGroup ag115 = new AccountGroup(ACCOUNT.A, "accumulated depreciation - vehicles", ag1.getId(), false, true);
                schemaSession.save(ag115);
                AccountGroup ag116 = new AccountGroup(ACCOUNT.A, "furnitures", ag1.getId(), false, true);
                schemaSession.save(ag116);
                AccountGroup ag117 = new AccountGroup(ACCOUNT.A, "accumulated depreciation - furnitures", ag1.getId(), false, true);
                schemaSession.save(ag117);

                AccountGroup ag3 = new AccountGroup(ACCOUNT.A, "cash", ag.getId(), false, false);
                schemaSession.save(ag3);
                AccountGroup ag31 = new AccountGroup(ACCOUNT.A, "cash account", ag3.getId(), false, true);
                schemaSession.save(ag31);
                AccountGroup ag4 = new AccountGroup(ACCOUNT.A, "bank", ag.getId(), false, false);
                schemaSession.save(ag4);
                AccountGroup ag5 = new AccountGroup(ACCOUNT.A, "investments", ag.getId(), false, false);
                schemaSession.save(ag5);
                AccountGroup ag6 = new AccountGroup(ACCOUNT.A, "receivables", ag.getId(), false, false);
                schemaSession.save(ag6);
                AccountGroup ag7 = new AccountGroup(ACCOUNT.A, "inventory", ag.getId(), false, false);
                schemaSession.save(ag7);
                AccountGroup ag71 = new AccountGroup(ACCOUNT.A, "inventory", ag7.getId(), false, true);
                schemaSession.save(ag71);
                AccountGroup ag711 = new AccountGroup(ACCOUNT.A, "raw material", ag7.getId(), false, true);
                schemaSession.save(ag711);
                AccountGroup ag8 = new AccountGroup(ACCOUNT.A, "prepaid expenses", ag.getId(), false, false);
                schemaSession.save(ag8);
                AccountGroup ag9 = new AccountGroup(ACCOUNT.A, "other current assets", ag.getId(), false, false);
                schemaSession.save(ag9);
                AccountGroup ag10 = new AccountGroup(ACCOUNT.A, "loans and Advances", ag.getId(), false, false);
                schemaSession.save(ag10);
                AccountGroup ag11 = new AccountGroup(ACCOUNT.A, "deposits", ag.getId(), false, false);
                schemaSession.save(ag11);


                AccountGroup ag12 = new AccountGroup(ACCOUNT.L, "current liabilities", 0L, false, false);
                schemaSession.save(ag12);
                AccountGroup ag121 = new AccountGroup(ACCOUNT.L, "creditors", ag12.getId(), false, true);
                schemaSession.save(ag121);
                AccountGroup ag122 = new AccountGroup(ACCOUNT.L, "loans", ag12.getId(), false, false);
                schemaSession.save(ag122);

                AccountGroup ag13 = new AccountGroup(ACCOUNT.L, "duties & taxes", ag12.getId(), false, false);
                schemaSession.save(ag13);
                AccountGroup ag131 = new AccountGroup(ACCOUNT.L, "vat payable", ag13.getId(), false, true);
                schemaSession.save(ag131);
                AccountGroup ag1311 = new AccountGroup(ACCOUNT.L, "tds payable", ag13.getId(), false, true);
                schemaSession.save(ag1311);
                AccountGroup ag14 = new AccountGroup(ACCOUNT.L, "provisions", ag12.getId(), false, false);
                schemaSession.save(ag14);
                AccountGroup ag18 = new AccountGroup(ACCOUNT.L, "other current liabilities", ag12.getId(), false, false);
                schemaSession.save(ag18);


                AccountGroup ag25 = new AccountGroup(ACCOUNT.L, "equity", 0L, false, false);
                schemaSession.save(ag25);
                AccountGroup ag251 = new AccountGroup(ACCOUNT.L, "Owner's equity", ag25.getId(), false, true);
                schemaSession.save(ag251);
                AccountGroup ag252 = new AccountGroup(ACCOUNT.L, "capital surplus", ag25.getId(), false, true);
                schemaSession.save(ag252);
                AccountGroup ag253 = new AccountGroup(ACCOUNT.L, "opening balances", ag25.getId(), false, true);
                schemaSession.save(ag253);
                AccountGroup ag254 = new AccountGroup(ACCOUNT.L, "retained earnings", ag25.getId(), false, true);
                schemaSession.save(ag254);
                AccountGroup ag255 = new AccountGroup(ACCOUNT.L, "owner drawing", ag25.getId(), false, true);
                schemaSession.save(ag255);


                AccountGroup ag19 = new AccountGroup(ACCOUNT.L, "long term liabilities", 0L, false, false);
                schemaSession.save(ag19);


                AccountGroup ag20 = new AccountGroup(ACCOUNT.I, "income", 0L, false, false);
                schemaSession.save(ag20);
                AccountGroup ag201 = new AccountGroup(ACCOUNT.I, "sales income", ag20.getId(), false, true);
                schemaSession.save(ag201);
                AccountGroup ag2011 = new AccountGroup(ACCOUNT.I, "emi interest received", ag20.getId(), false, true);
                schemaSession.save(ag2011);
                AccountGroup ag2012 = new AccountGroup(ACCOUNT.I, "emi charge received", ag20.getId(), false, true);
                schemaSession.save(ag2012);
                AccountGroup ag202 = new AccountGroup(ACCOUNT.I, "service income", ag20.getId(), false, true);
                schemaSession.save(ag202);
                AccountGroup ag203 = new AccountGroup(ACCOUNT.I, "sales returns and allowances", ag20.getId(), false, true);
                schemaSession.save(ag203);

                AccountGroup ag21 = new AccountGroup(ACCOUNT.I, "other income", 0L, false, false);
                schemaSession.save(ag21);
                AccountGroup ag2111 = new AccountGroup(ACCOUNT.I, "donation income", ag21.getId(), false, false);
                schemaSession.save(ag2111);
                AccountGroup ag211 = new AccountGroup(ACCOUNT.I, "delivery income", ag21.getId(), false, true);
                schemaSession.save(ag211);
                AccountGroup ag212 = new AccountGroup(ACCOUNT.I, "discount received", ag21.getId(), false, true);
                schemaSession.save(ag212);
                AccountGroup ag213 = new AccountGroup(ACCOUNT.I, "gain/loss from disposal", ag21.getId(), false, true);
                schemaSession.save(ag213);

                AccountGroup ag23 = new AccountGroup(ACCOUNT.E, "general expenses", 0L, false, false);
                schemaSession.save(ag23);
                AccountGroup ag231 = new AccountGroup(ACCOUNT.E, "bank charges", ag23.getId(), false, true);
                schemaSession.save(ag231);
                AccountGroup ag232 = new AccountGroup(ACCOUNT.E, "amount mismatch", ag23.getId(), false, true);
                schemaSession.save(ag232);
                AccountGroup ag233 = new AccountGroup(ACCOUNT.E, "bad debt", ag23.getId(), false, true);
                schemaSession.save(ag233);
                AccountGroup ag234 = new AccountGroup(ACCOUNT.E, "internet", ag23.getId(), false, true);
                schemaSession.save(ag234);
                AccountGroup ag2341 = new AccountGroup(ACCOUNT.E, "underbilling", ag23.getId(), false, true);
                schemaSession.save(ag2341);
                AccountGroup ag235 = new AccountGroup(ACCOUNT.E, "telephone", ag23.getId(), false, true);
                schemaSession.save(ag235);
                AccountGroup ag236 = new AccountGroup(ACCOUNT.E, "mobile recharge", ag23.getId(), false, true);
                schemaSession.save(ag236);
                AccountGroup ag237 = new AccountGroup(ACCOUNT.E, "office supplies", ag23.getId(), false, true);
                schemaSession.save(ag237);
                AccountGroup ag238 = new AccountGroup(ACCOUNT.E, "rent", ag23.getId(), false, true);
                schemaSession.save(ag238);
                AccountGroup ag2381 = new AccountGroup(ACCOUNT.E, "repairs & maintenance", ag23.getId(), false, true);
                schemaSession.save(ag2381);
                AccountGroup ag239 = new AccountGroup(ACCOUNT.E, "salaries & wages", ag23.getId(), false, true);
                schemaSession.save(ag239);
                AccountGroup ag2310 = new AccountGroup(ACCOUNT.E, "travel", ag23.getId(), false, true);
                schemaSession.save(ag2310);
                AccountGroup ag2311 = new AccountGroup(ACCOUNT.E, "depreciation expenses", ag23.getId(), false, true);
                schemaSession.save(ag2311);
                AccountGroup ag23112 = new AccountGroup(ACCOUNT.E, "rounding expenses", ag23.getId(), false, true);
                schemaSession.save(ag23112);
                AccountGroup ag23113 = new AccountGroup(ACCOUNT.E, "discount given", ag23.getId(), false, true);
                schemaSession.save(ag23113);
                AccountGroup ag231131 = new AccountGroup(ACCOUNT.E, "employee project expenses", ag23.getId(), false, true);
                schemaSession.save(ag231131);

                AccountGroup ag22 = new AccountGroup(ACCOUNT.E, "cost of goods sold", 0L, false, false);
                schemaSession.save(ag22);
                AccountGroup ag221 = new AccountGroup(ACCOUNT.E, "cost of goods sold", ag22.getId(), false, true);
                schemaSession.save(ag221);
                AccountGroup ag223 = new AccountGroup(ACCOUNT.E, "inventory adjustment", ag22.getId(), false, true);
                schemaSession.save(ag223);
                AccountGroup ag224 = new AccountGroup(ACCOUNT.E, "packing & forwarding", ag22.getId(), false, true);
                schemaSession.save(ag224);
                AccountGroup ag222 = new AccountGroup(ACCOUNT.E, "purchase returns & allowances", ag22.getId(), false, true);
                schemaSession.save(ag222);

                AccountGroup ag777 = new AccountGroup(ACCOUNT.L, "security deposit", ag18.getId(), false, true);
                schemaSession.save(ag777);

                logger.info("Default accounts Generated.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            schemaTransaction.rollback();
            logger.error("Error Occurred initializing default data for schema " + schema);
            throw e;
        }
    }
}
