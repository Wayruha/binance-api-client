package com.binance.api.client.domain.account;

import com.binance.api.client.constant.BinanceApiConstants;
import com.fasterxml.jackson.annotation.*;
import lombok.*;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.math.BigDecimal;

/**
 * An asset balance in an Account.
 *
 * @see Account
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class AssetBalance {

    /**
     * Asset symbol.
     */
    @JsonAlias({"a", "asset"})
    private String asset;

    /**
     * Available balance.
     */
    @JsonAlias({"f", "free"})
    private String free;

    /**
     * Locked by open orders.
     */
    @JsonAlias({"l", "locked"})
    private String locked;

    public static AssetBalance emptyBalance(String asset) {
        AssetBalance bal = new AssetBalance();
        bal.setAsset(asset);
        bal.setFree("0");
        bal.setLocked("0");
        return bal;
    }

    public BigDecimal getTotalBalance() {
        final BigDecimal _free = free == null ? BigDecimal.ZERO : new BigDecimal(free);
        final BigDecimal _locked = locked == null ? BigDecimal.ZERO : new BigDecimal(locked);
        return _free.add(_locked);
    }

    @JsonIgnore
    public boolean isNoFreeBalance() {
        return free == null || new BigDecimal(free).signum() == 0;
    }

    @JsonIgnore
    public boolean isNoLockedBalance() {
        return locked == null || new BigDecimal(locked).signum() == 0;
    }

    @JsonIgnore
    public boolean isEmpty() {
        return isNoFreeBalance() && isNoLockedBalance();
    }

    public void addBalance(BigDecimal delta) {
        BigDecimal initial = new BigDecimal(free);
        BigDecimal newBalance = initial.add(delta);
        setFree(newBalance.toPlainString());
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, BinanceApiConstants.TO_STRING_BUILDER_STYLE)
                .append("asset", asset)
                .append("free", free)
                .append("locked", locked)
                .toString();
    }
}
