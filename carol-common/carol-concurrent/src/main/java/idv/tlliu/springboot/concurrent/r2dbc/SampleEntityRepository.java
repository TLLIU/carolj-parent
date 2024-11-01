package idv.tlliu.springboot.concurrent.r2dbc;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.data.r2dbc.mapping.OutboundRow;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.r2dbc.core.Parameter;
import org.springframework.stereotype.Repository;

import idv.tlliu.springboot.concurrent.domain.SampleEntity;
import io.r2dbc.spi.Row;
import reactor.core.publisher.Flux;

@Repository
public interface SampleEntityRepository extends R2dbcRepository<SampleEntity, Long> {
  
  @Query("select * from SampleEntity se where LOWER(se.label) like LOWER(concat('%', concat(:label, '%')))")
  Flux<SampleEntity> findByLabelContaining(String label);

	@ReadingConverter
	public static class SampleEntityReadingConverter implements Converter<Row, SampleEntity> {

		@Override
		public SampleEntity convert(Row source) {
			return new SampleEntity(source.get("id", Long.class), source.get("label", String.class));
		}
	}

	@WritingConverter
	public static class SampleEntityWritingConverter implements Converter<SampleEntity, OutboundRow> {

		@Override
		public OutboundRow convert(SampleEntity source) {
			final var row = new OutboundRow();
			row.put("id", Parameter.from(source.getId()));
			row.put("label", Parameter.from(source.getLabel()));
			return row;
		}
	}
}