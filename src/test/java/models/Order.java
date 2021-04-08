package models;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import models.base.BaseModel;
import models.enums.OrderStatusEnum;

import static com.fasterxml.jackson.annotation.JsonFormat.DEFAULT_TIMEZONE;

@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
@AllArgsConstructor
public class Order extends BaseModel {
    static private final String DATA_TIME_FORMAT = "YYYY-MM-DD'T'HH:mm:ss.SSS'Z'";

    @Getter
    @Setter
    private Long id;
    @Getter
    @Setter
    private Long petId;
    @Getter
    @Setter
    private Integer quantity;
    @Getter
    @Setter
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATA_TIME_FORMAT, timezone = DEFAULT_TIMEZONE)
    private Date shipDate;

    @Getter
    @Setter
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private OrderStatusEnum status;

    @Getter
    @Setter
    private Boolean complete;

    public Order() {
        super();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Order order = (Order) o;
        return Objects.equals(this.id, order.id) &&
                Objects.equals(this.petId, order.petId) &&
                Objects.equals(this.quantity, order.quantity) &&
                Objects.equals(this.shipDate, order.shipDate) &&
                Objects.equals(this.status, order.status) &&
                Objects.equals(this.complete, order.complete);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, petId, quantity, shipDate, status, complete);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Order {\n");
        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    petId: ").append(toIndentedString(petId)).append("\n");
        sb.append("    quantity: ").append(toIndentedString(quantity)).append("\n");
        sb.append("    shipDate: ").append(toIndentedString(new SimpleDateFormat(DATA_TIME_FORMAT).format(shipDate))).append("\n");
        sb.append("    status: ").append(toIndentedString(status)).append("\n");
        sb.append("    complete: ").append(toIndentedString(complete)).append("\n");
        sb.append("}");
        return sb.toString();
    }
}

