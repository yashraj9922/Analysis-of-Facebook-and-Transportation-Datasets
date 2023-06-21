# dataset paper link - https://arxiv.org/pdf/1802.03997.pdf
Title - Graph Embedding with Self Clustering
Contributors - Benedek Rozemberczki, Ryan Davies, Rik Sarkar and Charles Sutton

GEMSEC wants to solve the problem of embedding and self clustering simultaneously and hence each solution improves the other.

The algorithm is based on the paradigm of sequence based node embeddings

Dataset
The dataset is collected originally from real world data using Facebook APIs

Cluster Quality
To enhance the cluster quality, assumption is made that a single node belongs to a single community

Results of hypersensitivity analysis
1) high quality clusters for a wide range of parameter settings were obtained
2) Introducing smoothness regularization makes the GEMSEC models more robust to hypersensitivity
Changes in the length of truncated random walks and the number of random walks per source node has only a marginal effect on the community detection performance.
