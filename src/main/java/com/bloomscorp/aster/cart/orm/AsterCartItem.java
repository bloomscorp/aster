package com.bloomscorp.aster.cart.orm;

import com.bloomscorp.behemoth.orm.BehemothORM;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public abstract class AsterCartItem extends BehemothORM {
}
