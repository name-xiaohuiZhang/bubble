# 对抗性机器学习

此文由Alexey Kurakin，Ian J. Goodfellow发表于2017ICLR.

[原文链接](https://arxiv.org/pdf/1611.01236.pdf)

## 摘要

对抗性示例是旨在欺骗机器学习模型的恶意输入。 它们经常从一个模型转移到另一个模型，允许攻击者在不知道目标模型参数的情况下发动黑盒攻击。 对抗性训练是在对抗性示例上明确训练模型的过程，以使其对与袭击更鲁棒或者减少在干净输入上的测试错误。到目前为止，对抗性训练主要适用于小问题。 在本研究中，我们将对抗训练应用于ImageNet。我们的贡献包括：（1）关于如何成功地将对抗性训练扩展到大型模型和数据集的建议，（2）对抗性训练赋予单步攻击方法稳健性的观察，（3）多步攻击方法的发现是 比单步攻击方法更不易转移，因此单步攻击最适合安装黑匣子攻击，以及（4）解决“标签泄漏”效应，使得对抗训练的模型在对抗性示例上比在干净的示例上表现更好， 因为对抗性示例构造过程使用真实标签，并且模型可以学习在构造过程中利用规律性。

## 标签泄露



我们说，当且仅当模型使用真实标签生成对抗性示例时才正确分类特定示例的标签，但在不使用真实标签的情况下创建的相应对抗示例却被错误分类了。 如果泄漏的标签太多，那么对抗性示例的准确性可能会大于我们在ImageNet数据集上观察到的清晰示例的准确性。

我们相信这种效果的发生是因为使用真实标签的一步法执行了一个非常简单且可预测的转换，模型可以学习识别。因此，对抗性示例构造过程无意中将关于真实标签的信息泄漏到输入中。我们发现如果我们在对抗性示例的构造过程中不使用真实标签，这样的效果就会消失。如果使用迭代方法，效果也会消失，可能是因为迭代过程的输出比一步过程的输出更加多样化且更不可预测。

总体而言，由于标签泄漏效应，我们不建议使用FGSM或针对真实类别标签定义的其他方法来评估对抗性示例的稳健性; 我们建议使用不直接访问标签的其他一步法。

我们建议将真实标签替换为模型预测的最可能标签。 或者，给定干净的输入，并且在给定扰动输入的情况下在所有预测标签上的分布，可以最大化在所有预测标签上的完全分布之间的交叉熵[Distributional
smoothing with virtual adversarial training.]()
