package shop.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "RamenProduct")
public class RamenProduct {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "type")
	private String type;

	@Column(name = "ramen_title")
	private String title;

	@Column(name = "ramen_price")
	private Integer price;

	@Column(name = "pc_name")
	private String PcName;
	@CreatedDate
	@LastModifiedDate
	@Column(name = "CREATE_TIME")
	private Date createTime;
	@Column(name = "sales", columnDefinition = "int default 0")
	private Integer count;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public String getPcName() {
		return PcName;
	}

	public void setPcName(String pcName) {
		PcName = pcName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCreateTime() {
		if (createTime != null) {
			SimpleDateFormat DF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return DF.format(createTime);
		}
		return "沒有！！";
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {

		this.count = count;
	}
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
