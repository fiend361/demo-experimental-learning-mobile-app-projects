import 'package:rxdart/rxdart.dart';
import '../models/item_model.dart';
import '../resources/repository.dart';
import 'dart:async';

class StoriesBloc {
  final _repository = new Repository();
  final _topIds = PublishSubject<List<int>>();
  final _itemsOutput = BehaviorSubject<Map<int, Future<ItemModel>>>();
  final _itemFetcher = PublishSubject<int>();

  // Getters to streams
  Stream<List<int>> get topIds => _topIds.stream;
  Stream<Map<int, Future<ItemModel>>> get items => _itemsOutput.stream;

  // Getters to sinks
  Function(int) get fetchItem => _itemFetcher.sink.add;

  StoriesBloc() {
    _itemFetcher.stream.transform(_itemsTransformer()).pipe(_itemsOutput);
    // print('construct');
  }

  fetchTopIds() async {
    final ids = await _repository.fetchTopIds();
    // print(ids);
    _topIds.sink.add(ids);
  }

  _itemsTransformer() {
    // final k = await _repository.fetchItem(26979879);
    // print(k.title);
    return ScanStreamTransformer(
      (Map<int, Future<ItemModel>> cache, int id, index) {
        // print('id');
        cache[id] = _repository.fetchItem(id);
        return cache;
      },
      <int, Future<ItemModel>>{},
    );
  }

  dispose() {
    _topIds.close();
    _itemFetcher.close();
    _itemsOutput.cast();
  }
}
