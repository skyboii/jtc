import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;



public class JsonToCsv {

	public static void main(String[] args) {
		try {
			JsonNode temp = new ObjectMapper().readTree(new File("json1.json"));
			CsvSchema.Builder temp_sc = CsvSchema.builder();
			JsonNode obj = temp.elements().next();
			obj.fieldNames().forEachRemaining(fieldName -> {temp_sc.addColumn(fieldName);} );
			CsvSchema csv_sc = temp_sc.build().withHeader();
			CsvMapper csvMapper = new CsvMapper();
			csvMapper.writerFor(JsonNode.class)
			  .with(csv_sc)
			  .writeValue(new File("csv1.csv"), temp);
			
		} catch (JsonProcessingException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}

	}

}
