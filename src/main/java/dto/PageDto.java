package dto;

import java.util.List;

public record PageDto(int page, int pageCount, List<String> list) {

}
