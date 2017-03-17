/*
 * This file is part of bisq.
 *
 * bisq is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or (at
 * your option) any later version.
 *
 * bisq is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public
 * License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with bisq. If not, see <http://www.gnu.org/licenses/>.
 */

package io.bisq.wire.payload.payment;

import io.bisq.common.app.Version;
import io.bisq.wire.proto.Messages;

public final class USPostalMoneyOrderAccountPayload extends PaymentAccountPayload {
    // That object is sent over the wire, so we need to take care of version compatibility.
    private static final long serialVersionUID = Version.P2P_NETWORK_VERSION;

    private String postalAddress;
    private String holderName;


    public USPostalMoneyOrderAccountPayload(String paymentMethod, String id, long maxTradePeriod) {
        super(paymentMethod, id, maxTradePeriod);
    }

    public USPostalMoneyOrderAccountPayload(String paymentMethodName, String id, long maxTradePeriod,
                                            String postalAddress, String holderName) {
        super(paymentMethodName, id, maxTradePeriod);
        this.postalAddress = postalAddress;
        this.holderName = holderName;
    }

    public void setPostalAddress(String postalAddress) {
        this.postalAddress = postalAddress;
    }

    public String getPostalAddress() {
        return postalAddress;
    }

    public String getHolderName() {
        return holderName;
    }

    public void setHolderName(String holderName) {
        this.holderName = holderName;
    }

    @Override
    public String getPaymentDetails() {
        return "US Postal Money Order - Holder name: " + holderName + ", postal address: " + postalAddress;
    }


    @Override
    public String getPaymentDetailsForTradePopup() {
        return "Holder name: " + holderName + "\n" +
                "Postal address: " + postalAddress;
    }

    @Override
    public Messages.PaymentAccountPayload toProtoBuf() {
        Messages.USPostalMoneyOrderAccountPayload.Builder thisClass =
                Messages.USPostalMoneyOrderAccountPayload.newBuilder()
                        .setPostalAddress(postalAddress)
                        .setHolderName(holderName);
        Messages.PaymentAccountPayload.Builder paymentAccountPayload =
                Messages.PaymentAccountPayload.newBuilder()
                        .setId(id)
                        .setPaymentMethodId(paymentMethodId)
                        .setMaxTradePeriod(maxTradePeriod)
                        .setUSPostalMoneyOrderAccountPayload(thisClass);
        return paymentAccountPayload.build();
    }
}