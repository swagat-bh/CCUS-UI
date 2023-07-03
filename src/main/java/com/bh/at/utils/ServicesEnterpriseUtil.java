package com.bh.at.utils;

import java.util.HashSet;

import com.bh.icommonallutil.IJSONA;
import com.bh.icommonallutil.IJSONO;

public class ServicesEnterpriseUtil {
  private String enterpriseId;
  public HashSet<String> enterpriseList = new HashSet<String>();

  public String getEnterpriseId() {
    return enterpriseId;
  }

  public void setEnterpriseId(String enterpriseId) {
    this.enterpriseId = enterpriseId;
  }

  public void extractEnterpriseId(IJSONO requestBody, String serviceName) {
    IJSONA extractlist = requestBody.get("body");
    switch (serviceName.toUpperCase()) {

      case "ASSET" -> {
        IJSONO extract = extractlist.get(0);
        this.enterpriseId = extract.get("Group");
        enterpriseList.add(enterpriseId);
        break;
      }
      case "EVENT", "ALERT" -> {
        IJSONO extract = extractlist.get(0);
        IJSONA alertsList = extract.get("alerts");
        this.enterpriseId = alertsList.getJSONO(0).get("groupid").toString();
        enterpriseList.add(enterpriseId);
        break;
      }

    }
  }
}


