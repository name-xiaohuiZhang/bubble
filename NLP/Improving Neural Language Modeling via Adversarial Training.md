# 文本输入嵌入空间中可解释的对抗扰动

Improving Neural Language Modeling via Adversarial Training

Dilin Wang 发表于2019ICML

## 摘要

最近，通过使用深度神经网络，语言模型已经取得了实质性的进展。然而，大规模的神经语言模型确是容易过拟合。在本文中，我们提出了一种简单但高效的对抗训练机制，用于正则化神经语言模型。这个想法是在训练模型时向输出嵌入层引入对抗性噪声。我们证明了最优的对抗性噪声产生了一种简单的闭合形式解决方案，从而使我们能够开发出一种简单且时间有效的算法。从理论上讲，我们表明我们的对抗机制有效地鼓励了嵌入向量的多样性，有助于提高模型的鲁棒性。根据经验，我们证明我们的方法改进了Penn Treebank（PTB）和Wikitext-2上语言建模的单一模型最新结果，分别达到了46.01和38.07的测试困惑度分数。当应用于机器翻译时，我们的方法改进了WMT14英语 - 德语和IWSLT14德语 - 英语任务中BLEU分数的各种基于transformer的翻译基线。

## 介绍

统计语言建模是机器学习中的一项基本任务，在各个领域都有广泛的应用，包括自动语音识别，机器翻译和计算机视觉等。最近，深度神经网络模型，尤其是基于递归神经网络（RNN）的模型，已经成为最有效的语言建模方法之一。不幸的是，训练大规模基于RNN的语言模型的一个主要挑战是它们过度拟合的倾向;这是由于RNN模型的高度复杂性和语言输入的离散性质造成的。 虽然已经研究了各种正规化技术，例如早期停止和dropout，但是在最先进的基准测试中仍然普遍观察到严重的过度拟合即训练性能和测试性能存在巨大的误差

在本文中，我们开发了一种简单高效的正则化策略。我们的想法是在语言模型的softmax层中对单词嵌入向量注入对抗性扰动，找到最优参数，以最大限度地应对受到对抗扰动的最坏情况。重要的是，我们证明了最优扰动向量在我们的构造下产生了一种简单且计算有效的形式，允许我们推导出一种简单快速的训练算法（参见算法1），该算法可以通过对标准最大似然的微小修改轻松实现 并没有引入额外的训练参数。我们方法的一个有趣的理论属性是它提供了一种有效的机制来鼓励词嵌入向量的多样性，这被广泛认为在神经语言模型中产生更好的泛化性能。

在以前的工作中，通常通过添加额外的多种惩罚项来明确地实施多样性，这可能影响似然优化并且当词汇量大时计算成本高。有趣的是，我们表明，我们的对抗性训练有效地实施了多样性，而没有明确引入额外的多种惩罚项，并且比直接正则化具有更高的计算效率。

根据经验，我们发现我们的对抗方法可以显着提高最先进的大规模神经语言建模和机器翻译的性能。对于语言建模，我们根据我们的知识为Penn Treebank（PTB）和WikiText-2（WT2）数据集建立新的单一模型最新结果，分别达到46.01和38.07测试困惑得分。

为了证明该方法的广泛适用性，我们还应用我们的方法来改进机器翻译，使用Transformer作为我们的基本模型。 通过结合我们的对抗性训练，我们在WMT2014英语 - 德语和IWSTL2014德语英语翻译上改进了各种基于Transformer的翻译基线。

## 背景：神经语言模型

典型的单词级语言模型被指定为使用链规则的条件概率的乘积：

## 我们的方法

我们提出了一种简单的算法，可以有效地减轻深度神经语言模型中的过度拟合，基于在softmax函数中对输出嵌入向量wi注入对抗扰动（方程（3））。 我们的方法非常简单，几乎没有额外的计算开销超过标准的最大似然训练，同时在具有挑战性的基准测试中实现了实质性的改进（见第5节）。 我们还对这个简单的机制得出了理论上的见解，表明它隐含地促进了输出嵌入向量之间的多样性{wi}，人们普遍认为这会增加结果的稳健性。

我们表明，根据AdvSoft，一旦存在wi支配其他单词的上下文向量h，保证嵌入向量wi与所有其他单词的嵌入向量分开至少距离l。这是对抗性设置的定义暗示的一个简单属性：如果在wi的l-ball中存在wj，则wi（和wj）永远不会支配另一个，因为胜利者总是受到对抗性扰动的惩罚。

注意，最大化对抗训练目标函数可以被视为强制通过其对应的上下文向量h来识别每个wi，并且因此隐含地强制识别的单词与其他单词之间的多样性。 我们应该注意到定义3.2中的上下文向量h不必存在于训练集中，尽管由于训练它更可能发生在训练集中。事实上，我们可以在成对距离和 对抗性softmax功能。

## 相关工作

与这些工作相比，我们的工作利用对抗性思想作为专门用于神经语言模型的正则化技术，并专注于仅在softmax层上引入对抗性噪声，从而可以获得简单的闭合形式解决方案。

我们的方法通过理论保证隐含地促进了多样性（diversity），并且没有引入计算开销。

## 结论

在这项工作中，我们提出了一种用于神经语言建模的对抗性MLE训练策略，该策略可以促进嵌入空间的多样性并提高泛化性能。 我们的方法可以很容易地用作标准MLE模型的直接替代，而无需额外的训练参数和计算开销。 将此方法应用于各种语言建模和机器翻译任务，我们在标准基准测试上实现了对最先进基线模型的改进。