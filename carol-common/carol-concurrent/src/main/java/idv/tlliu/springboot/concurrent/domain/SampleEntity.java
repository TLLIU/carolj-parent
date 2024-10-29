package idv.tlliu.springboot.concurrent.domain;

import org.springframework.data.annotation.Id;

public class SampleEntity {
	public SampleEntity(Long id, String label) {
    this.id = id;
    this.label = label;
  }
	
  public SampleEntity() {
  }
	
  @Id
	private Long id;

	private String label;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getLabel() {
    return label;
  }

  public void setLabel(String label) {
    this.label = label;
  }
	
	
}
