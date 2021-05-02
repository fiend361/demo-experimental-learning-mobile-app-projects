import 'dart:async';
import 'package:ghat_news/main.dart';

import 'news_api_provider.dart';
import 'news_db_provider.dart';
import '../models/item_model.dart';


class Repository {
  List<Source> sources = <Source>[
    // newsDbProvider,
    NewsApiProvider(),
  ];

  List<Cache> caches = <Cache>[
    // newsDbProvider,
  ];


  // TODO: Iterate over sources after
  // TODO: implementing fetchTopIds in db_provider
  Future<List<int>> fetchTopIds() {
    return sources[0].fetchTopIds();
  }

  Future<ItemModel> fetchItem(int id) async {
    ItemModel item;
    var source;

    for (source in sources) {
      item = await source.fetchItem(id);
      if (item != null) {
        break;
      }
    }

    // for (var cache in caches) {
    //   if (cache != source) {
    //     cache.addItem(item);
    //   }
    // }

    return item;
  }
}


abstract class Source {
  Future<List<int>> fetchTopIds();
  Future<ItemModel> fetchItem(int id);
}


abstract class Cache {
  Future<int> addItem(ItemModel item);
}




