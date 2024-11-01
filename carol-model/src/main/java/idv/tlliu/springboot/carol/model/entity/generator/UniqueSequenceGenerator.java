package idv.tlliu.springboot.carol.model.entity.generator;

import java.util.EnumSet;

import org.aspectj.weaver.Member;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.generator.BeforeExecutionGenerator;
import org.hibernate.generator.EventType;
import org.hibernate.generator.EventTypeSets;
import org.hibernate.generator.GeneratorCreationContext;

public class UniqueSequenceGenerator implements BeforeExecutionGenerator {
  UniqueSequenceId uniqueSequenceId;
  public UniqueSequenceGenerator(UniqueSequenceId sequenceId, Member annotatedMember, GeneratorCreationContext creationContext) {
    this.uniqueSequenceId = sequenceId;
  }

  @Override
  //此方法返回是插入还是更新生效，此处通过注解动态指定
  public EnumSet<EventType> getEventTypes() {
    return EventTypeSets.fromArray(uniqueSequenceId.event());
  }

  @Override
  //此方法返回要填充的值
  public Object generate(SharedSessionContractImplementor session, Object owner, Object currentValue,
      EventType eventType) {
    // 例如 entity 已经设置了值，则直接使用设置的值
    if (currentValue != null) {
      return currentValue;
    }
    
    return null;
  }

}
