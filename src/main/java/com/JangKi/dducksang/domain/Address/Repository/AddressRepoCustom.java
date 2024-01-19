package com.JangKi.dducksang.domain.Address.Repository;

import java.util.List;

public interface AddressRepoCustom {

    List<String> searchAll();

    List<String> searchAllByDISTINCT();

    List<String> searchAllByGroupBy();
}
