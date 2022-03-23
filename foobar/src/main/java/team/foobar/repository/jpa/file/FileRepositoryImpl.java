package team.foobar.repository.jpa.file;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import team.foobar.domain.File;

import java.util.List;

import static team.foobar.domain.QFile.file;

@RequiredArgsConstructor
public class FileRepositoryImpl implements FileRepositoryCustom{
    private final JPAQueryFactory factory;

    @Override
    public List<File> findByTableAndId(String table, Integer id) {
        return factory.selectFrom(file).where(file.targetTable.eq(table).and(file.targetId.eq(id))).fetch();
    }
}
