package idv.tlliu.springboot.concurrent.web.dtos;

import java.io.Serializable;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class SampleResponseDto implements Serializable {
	public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getMappedLabel() {
    return mappedLabel;
  }

  public void setMappedLabel(String mappedLabel) {
    this.mappedLabel = mappedLabel;
  }

  private static final long serialVersionUID = -1107179772146345245L;
	
	public SampleResponseDto() {
	  
	}
	
	public SampleResponseDto(@NotNull long id, @NotNull @NotEmpty String label) {
	  this.id = id;
	  this.mappedLabel = label;
	}
	
	@NotNull
	private Long id;

	@NotNull
	@NotEmpty
	private String mappedLabel;
}