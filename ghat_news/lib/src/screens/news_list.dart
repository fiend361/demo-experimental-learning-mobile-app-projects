import 'package:flutter/material.dart';
import 'package:ghat_news/src/blocs/stories_bloc.dart';
import '../blocs/stories_provider.dart';
import '../widgets/news_list_tile.dart';
import '../resources/news_db_provider.dart';

class NewsList extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    // print(newsDbProvider.db.path);
    final bloc = StoriesProvider.of(context);

    bloc.fetchTopIds();

    return Scaffold(
      appBar: AppBar(
        title: Text('Top News'),
      ),
      body: buildList(bloc),
    );
  }

  Widget buildList(StoriesBloc bloc) {
    return StreamBuilder(
      stream: bloc.topIds,
      builder: (context,AsyncSnapshot<List<int>> snapshot) {
        if (!snapshot.hasData) {
          return Center(
            child: CircularProgressIndicator(),
          );
        }

        return ListView.builder(
          itemCount: snapshot.data.length,
          itemBuilder: (context, int index) {
            return NewsListTile(
              itemId: snapshot.data[index],
            );
          },
        );
      },
    );
  }
}
