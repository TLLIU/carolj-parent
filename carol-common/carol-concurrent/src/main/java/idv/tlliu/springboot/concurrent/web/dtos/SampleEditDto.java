package idv.tlliu.springboot.concurrent.web.dtos;

import java.io.Serializable;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class SampleEditDto implements Serializable {
	private static final long serialVersionUID = 2734365053999872845L;
	
	
	public SampleEditDto(@NotNull @NotEmpty String mappedLabel) {
    super();
    this.mappedLabel = mappedLabel;
  }

	public SampleEditDto() {
    super();
  }


  @NotNull
	@NotEmpty
	private String mappedLabel;


  public String getMappedLabel() {
    return mappedLabel;
  }

  public void setMappedLabel(String mappedLabel) {
    this.mappedLabel = mappedLabel;
  }
  
  
}