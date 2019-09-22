# 交叉视图训练的半监督序列模型

此篇论文由斯坦福，谷歌大脑Kevin Clark， Minh-Thang Luong发表于2018EMNLP

[原文链接](https://arxiv.org/pdf/1809.08370.pdf)

## 摘要

无监督的表示学习算法（例如word2vec和ELMo）提高了许多有监督的NLP模型的准确性，这主要是因为它们可以利用大量未标记的文本。但是，监督模型仅在主要训练阶段从任务特定的标记数据中学习。因此，我们提出了交叉视图训练（CVT），这是一种半监督学习算法，可使用标记和未标记数据的混合来改进Bi-LSTM句子编码器的表示形式。在标记的示例中，使用标准的监督学习。在未标记的示例中，CVT教导辅助预测模块，该模块可以看到输入的受限视图（例如，仅句子的一部分），以匹配看到整个输入的完整模型的预测。由于辅助模块和完整模型共享中间表示，因此又改进了完整模型。此外，我们证明了CVT与多任务学习相结合时特别有效。我们在五个序列标记任务，机器翻译和依赖项解析上评估了CVT，从而获得了最先进的结果。

## 介绍

在神经NLP领域，非常成功的半监督学习策略就是预训练词向量[Distributed representations of words and phrases and their compositionality. In NIPS](http://papers.nips.cc/paper/5021-distributed-representations-of-words-and-phrases-and-their-compositionality.pdf)。最近的工作训练Bi-LSTM句子编码器进行语言建模，然后将其上下文相关表示形式合并到受监督的模型中。这种预训练方法先对大量未标记数据集执行无监督的表示学习，然后进行有监督的训练-该模型尝试学习一般有效的表示形式，而不是针对特定任务的表示形式。

